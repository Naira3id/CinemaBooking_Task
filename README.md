## Questions & Answers

**Q1. Why did we have to override `hashCode()` and `equals()`?**

`HashMap` and `HashSet` rely on hashing and object equality to locate entries. By default, Java compares memory addresses (`==`). Since the program creates new `Seat` objects for every operation, overriding `equals()` ensures two seats with the same `row` and `number` are treated as identical, while overriding `hashCode()` ensures they map to the same hash bucket.

**Q2. Delete your `hashCode()` but keep `equals()`, and run again. What happens? Why?**

Lookups (`get()`, `containsKey()`, `remove()`) fail. Java requires equal objects to have the same hash code. Without a custom `hashCode()`, `put()` places the object in one bucket based on its memory address, while `get()` searches a different bucket using the new object's memory address.

**Q3. Why are `row` and `number` `final`? What could go wrong if the number of a booked seat could be changed after it was put in the map?**

They are `final` to guarantee immutability. If a key's field is modified after insertion, its `hashCode()` changes, leaving the entry stranded in the wrong bucket where future lookups or removals cannot find it.

**Q4. Change `equals(Object o)` to `equals(Seat o)`. What happens? Then also remove `@Override` and run again. What happens now?**

Changing the parameter overloads `equals` instead of overriding `Object.equals(Object)`. `HashMap` still invokes `Object.equals(Object)`, falling back to reference equality (`==`) so lookups fail. Removing `@Override` hides this mistake and allows the buggy code to compile.

**BONUS. Replace the whole `Seat` class with `record Seat(char row, int number) {}`. What happens, and why?**

It runs and passes all tests because Java `record` classes are immutable and automatically generate proper `equals()`, `hashCode()`, and `toString()` methods based on their components.
