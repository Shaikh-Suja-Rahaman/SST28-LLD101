# Changes Made — Adapter Pattern Refactoring

## 1. Created `PaymentGateway` interface
- **What:** Defined a common interface with a single method: `String charge(String customerId, int amountCents)`
- **Why:** This is the **target interface** of the Adapter pattern. `OrderService` now depends only on this interface, not on any specific SDK. This removes tight coupling and allows new providers to be added without modifying `OrderService`.

## 2. Created `FastPayAdapter`
- **What:** A class that implements `PaymentGateway` and wraps `FastPayClient`.
- **How it works:** Accepts a `FastPayClient` via constructor injection and delegates the `charge()` call to `fp.payNow(customerId, amountCents)`.
- **Why:** `FastPayClient.payNow()` already has matching parameter types and order, so the adapter is a thin passthrough. But it still serves the purpose of decoupling `OrderService` from `FastPayClient`.

## 3. Created `SafeCashAdapter`
- **What:** A class that implements `PaymentGateway` and wraps `SafeCashClient`.
- **How it works:**
  - `SafeCashClient.createPayment(int amount, String user)` takes parameters in a **different order** than `charge(String customerId, int amountCents)` — so the adapter **reverses the argument order**.
  - `createPayment()` returns a `SafeCashPayment` object, which requires a second step — calling `.confirm()` to get the transaction ID string. The adapter handles this **two-step process** in a single `charge()` call.
- **Why:** This is the core value of the adapter — it translates mismatched interfaces (different parameter order + multi-step flow) into the common `PaymentGateway` interface.

## 4. Updated `App.java` — Registered adapters in the gateway map
- **What:** Instead of registering raw SDK clients, registered `FastPayAdapter` and `SafeCashAdapter` instances wrapping their respective clients.
- **Before (conceptual):** Raw SDKs would have been used directly.
- **After:**
  ```java
  gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
  gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));
  ```
- **Why:** The map now holds `PaymentGateway` implementations, so `OrderService` can call `charge()` uniformly without knowing which provider it's talking to.

## 5. Used `private final` for adapter fields
- **What:** Marked the SDK client fields in both adapters as `private final`.
- **Why:**
  - `private` — prevents external classes from accessing or modifying the internal SDK reference.
  - `final` — ensures the field is assigned only once (in the constructor) and can never be reassigned, making the adapter effectively **immutable and stateless**.

## 6. Used constructor injection (not `new` inside constructor)
- **What:** Initially, constructors were creating `new` instances internally and ignoring the passed-in parameter. Fixed to use the actual parameter passed in: `this.fp = fp;` and `this.sc = sc;`.
- **Why:** If the adapter ignores the injected dependency and creates its own, you lose the ability to pass in configured or mocked clients (e.g., for testing). Constructor injection keeps the adapter flexible and testable.

---

## Summary
| Before | After |
|---|---|
| `OrderService` would need to know about each SDK directly | `OrderService` only knows `PaymentGateway` |
| Adding a new provider = modifying `OrderService` | Adding a new provider = new adapter + register in map |
| Parameter mismatches handled in business logic | Parameter mismatches handled inside adapters |
| Tight coupling to third-party SDKs | Loose coupling via interface + adapters |