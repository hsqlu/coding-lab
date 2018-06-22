##SOLID Principles

The SOLID design principles help you to implement robust and maintainable applications. 

- Single Responsibility Principle

```
A class should have one, and only one, reason to change.
```

To follow this principle, your class isn’t allowed to have more than one responsibility, e.g., the management of entities or the conversion of data types. This avoids any unnecessary, technical coupling between responsibilities and reduces the probability that you need to change your class. It also lowers the complexity of each change because it reduces the number of dependent classes that are affected by it.


- Open/Closed Principle

```
Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.
```

- Liskov Substitution Principle

```
Let Φ(x) be a property provable about objects x of type T. Then Φ(y) should be true for objects y of type S where S is a subtype of T.
```
Don’t implement any stricter validation rules on input parameters than implemented by the parent class.
Apply at the least the same rules to all output parameters as applied by the parent class.


- Interface Segregation Principle

```
Clients should not be forced to depend upon interfaces that they do not use.
```

Similar to the Single Responsibility Principle, the goal of the Interface Segregation Principle is to reduce the side effects and frequency of required changes by splitting the software into multiple, independent parts.


- Dependency Inversion

High-level modules, which provide complex logic, should be easily reusable and unaffected by changes in low-level modules, which provide utility features.
 
```aidl
High-level modules should not depend on low-level modules. Both should depend on abstractions.
Abstractions should not depend on details. Details should depend on abstractions.
``` 
OOP Concepts

abstraction, encapsulation, inheritance, and polymorphism.