## This project only for GLI Testcase

### Singleton Note
Tell us, in which line you use spring boot singleton pattern:
```
src/$classpath/configuration/BreedConfiguration.java
```
In Spring, a singleton bean is a bean for which only one instance will be created in the application context. 

The **@Configuration** and **@Bean** annotations in the **BreedConfiguration** class are used to define the beans that will be managed by the Spring context. When the application context is created, Spring will create an instance of each of these beans and manage its lifecycle.
The **@Bean** annotation is used to indicate that a method produces a bean to be managed by the Spring context. 

The method annotated with **@Bean** will be called to produce the object that will be registered as a bean in the context. When the application context is refreshed, the beans will be created and initialized based on the configuration provided in the **@Configuration** class.

By default, all the beans defined in a **@Configuration** class are singleton beans. This means that there will only be one instance of each bean created in the context and this instance will be shared across all parts of the application that depend on it.