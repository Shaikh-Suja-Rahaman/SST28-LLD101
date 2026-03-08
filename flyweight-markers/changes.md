# Changes Made — Flyweight Pattern Refactoring

## Problem (Before)
Every `MapMarker` created its **own** `MarkerStyle` object via `new MarkerStyle(...)` inside its constructor. With 30,000 markers but only ~96 unique style combinations (3 shapes × 4 colors × 4 sizes × 2 filled), this meant **~29,904 duplicate objects** wasting memory.

---

## 1. Made `MarkerStyle` Immutable
- **What:** Made all fields `final` and removed any setters.
- **Why:** A flyweight object is **shared** across many markers. If it were mutable, one marker changing the style would accidentally affect all other markers sharing that instance. `final` fields guarantee this can never happen — making it safe to share.

---

## 2. Created `MarkerStyleFactory` (Flyweight Factory)
- **What:** A factory class that maintains a `HashMap<String, MarkerStyle>` cache. The `get()` method:
  1. Builds a key from the parameters: `"PIN|RED|12|F"`
  2. Checks if that key already exists in the cache
  3. If yes → returns the **existing** cached instance
  4. If no → creates a **new** `MarkerStyle`, stores it in the cache, and returns it
- **Why:** This is the core of the flyweight pattern. The factory is the **single point of creation** for `MarkerStyle` objects. It ensures that two markers requesting `"PIN|RED|12|F"` get back the **exact same object** in memory — not two equal-but-separate copies.
- **Depends on:** `MarkerStyle` being immutable (safe to share).

---

## 3. Changed `MapMarker` Constructor — Accept `MarkerStyle` Directly
- **What:**
  - **Before:** Constructor took raw parameters `(lat, lng, label, shape, color, size, filled)` and created `new MarkerStyle(...)` internally.
  - **After:** Constructor takes `(lat, lng, label, MarkerStyle style)` and stores the reference directly.
- **Why:** `MapMarker` should **not** be responsible for creating styles. It should only hold:
  - **Extrinsic state** (unique per marker): `lat`, `lng`, `label`
  - **A reference** to shared intrinsic state: `MarkerStyle`

  By accepting a `MarkerStyle` from outside, we allow the caller to pass in a **shared** instance from the factory.
- **Depends on:** `MarkerStyleFactory` existing to provide shared instances.

---

## 4. Updated `MapDataSource` — Use Factory Instead of `new MarkerStyle()`
- **What:**
  - Created a `private final MarkerStyleFactory msf` field — **one factory instance** for the entire data source.
  - Inside `loadMarkers()`, replaced `new MarkerStyle(...)` with `msf.get(shape, color, size, filled)`.
  - Passed the returned shared `MarkerStyle` into the `MapMarker` constructor.
- **Why:** This is where everything connects. The factory must be created **once** (outside the loop) so that its cache persists across all 30,000 iterations. If it were inside the loop, every iteration would get a fresh empty cache — defeating the purpose.
- **Depends on:** `MarkerStyleFactory` for caching, and `MapMarker` accepting a `MarkerStyle` parameter.

---

## Dependency Chain
```
MarkerStyle (immutable)
    ↑
MarkerStyleFactory (caches and returns shared MarkerStyle instances)
    ↑
MapDataSource (uses factory to get shared styles, passes them to MapMarker)
    ↑
MapMarker (holds extrinsic state + reference to shared MarkerStyle)
    ↑
App / MapRenderer (creates markers via DataSource, renders them)
```

---

## Result (After)

| Metric | Before | After |
|---|---|---|
| `MarkerStyle` objects for 30,000 markers | 30,000 | ≤ 96 |
| Memory per style duplication | Wasted | Shared |
| `MarkerStyle` mutable? | Yes (unsafe) | No (immutable, safe to share) |
| Style creation responsibility | Inside `MapMarker` | `MarkerStyleFactory` (single source of truth) |

Run `QuickCheck` to verify:
```
Markers: 20000
Unique style instances (by identity): ≤ 96
```