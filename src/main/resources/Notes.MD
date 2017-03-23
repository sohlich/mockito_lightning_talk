# Test object types

## Dummy
A dummy object is passed around but never used, i.e., its methods are never called. 
Such an object can for example be used to fill the parameter list of a method.

## Fake object
Fake objects have working implementations, but are usually simplified, 
for example they use an in memory database and not a real database.

## Stub
A stub class is an partial implementation for an interface or class with 
the purpose of using an instance of this stub class during testing. 
Stubs usually do responding at all to anything outside what's programmed in for the test. 
Stubs may also record information about calls

## Mock
A mock object is a dummy implementation for an interface or a class 
in which you define the output of certain method calls.
Mock objects are typically configured. 
Mock objects typically require less code to configure and should therefore be preferred.




