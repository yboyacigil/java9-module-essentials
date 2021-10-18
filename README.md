Java 9 Module (JPMS) Essentials
===
This is a demo project to experiment JPMS on a maven multi-module project to demonstrate:
- How to create a maven multi-module project that output JPMS modules depending on one another
- Use of maven plugin [Moditect](https://github.com/moditect/moditect) to generate runtime image 

# Noteworthy
Notes to myself on revisiting this:
- Tests of a JPMS module can only be accessed when you simply `open` the module, or you need to maintain different
`module-info.[java|test]` and use a bunch of plugins to make it run. See [this](https://github.com/sormuras/testing-in-the-modular-world) 
for details
- Creating runtime image using `jlink` would be so hard if you have many non-module dependencies. I cannot imagine if 
you try a spring-boot project. Moditect's plugin has `add-module-info` goal for this but it is so exhaustive if your 
dependencies are big.
- Using `ServiceLoader` to find service implementations provided by modules is good but what if you have dependencies to
initialize the service implementation?
- Maven compiler plugin (>= 3.7.0) is capable of handling JPMS requirements for compilation.
- If you use Lombok, better do `require static` since it is compile-time dependency and maven dependency scoped compile 
and optional. Maven compiler plugin needs to be configured separately for Lombok.