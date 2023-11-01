### Hexlet tests and linter status:
[![Actions Status](https://github.com/Vladimir-Serebrennikov/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Vladimir-Serebrennikov/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/5d61250b0f024ca145e6/maintainability)](https://codeclimate.com/github/Vladimir-Serebrennikov/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/5d61250b0f024ca145e6/test_coverage)](https://codeclimate.com/github/Vladimir-Serebrennikov/java-project-78/test_coverage)
# Data validator
### Description:
Data validator is a library that can be used to check the correctness of any data. First of all, we are talking about form data filled in by users. The [yup](https://github.com/jquense/yup) library is taken as a basis for the project.

It is three types of data supported for the moment: String, Integer (Number) and Map. To get started with the library just create a new Validator object:
``` java
Validator v = new Validator();
```
Then select one of the supported schemes (a new Validator object must be created for a new validation scheme, otherwise the library may not work correctly).

#### String Schema
String schema contains three validation methods:
* required() - string can not be null or empty
* minLength() - string must be longer than limiter, passed as parameter (or the same length)
* contains() - string must contain the substring, passed as parameter

``` java
Validator v = new Validator();
StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();
schema.isValid(""); // false
schema.isValid(null); // false
schema.isValid("Hello!"); // true

schema.minLength(2);
schema.isValid("Om"); // true
schema.isValid("Hello!"); // true
schema.isValid("."); // false

schema.contains("apple");
schema.isValid("I love apples"); // true;
schema.isValid("I like other fruits"); // false 
```

#### Number Schema
Number schema contains three validation methods:
* required() - number can not be null, passed data must be type Integer
* positive() - number must be more than zero
* range() - number must be greater than the minimum value passed as a parameter and less than the maximum value

``` java 
Validator v = new Validator();
NumberSchema schema = v.number();

schema.isValid(null); // true
schema.isValid(0); // true

schema.required();
schema.isValid(null); // false
schema.isValid(0); // true

schema.positive();
schema.isValid(0); // false
schema.isValid(-1); // false
schema.isValid(3); // true

schema.range(-2, 6);
schema.isValid(0); // false, because validation methods complement (not replace) each other
schema.isValid(-1); // false
schema.isValid(3); // true
```

#### Map Schema
Map schema contains three validation methods:
* required() - passed value must not equal null and must be a Map type
* sizeof() - number of key-value pairs must not be less than the transferred value
* shape() - allows you to describe validation for the values of each key of a Map object

``` java
Validator v = new Validator();
MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();
schema.isValid(null); // false

Map<String, String> data = new HashMap<>();
schema.isValid(data); // true

schema.sizeof(2);
schema.isValid(data); // false

data.put("key1", "value1");
data.put("key2", "value2");
schema.isValid(data); // true

Validator vMap = new Validator();
MapSchema mapSchema = vMap.map();
Map<String, BaseSchema> schemas = new HashMap<>();

schemas.put("name", vMap.string().required());
schemas.put("age", vMAp.number().positive());

mapSchema.shape(schemas);

Map<String, Object> person1 = new HashMap<>();
person1.put("name", "LukaMagic");
person1.put("age", 77);
mapSchema.isValid(person1); // true

Map<String, Object> person2 = new HashMap<>();
person2.put("name", "King James");
person2.put("age", null);
mapSchema.isValid(person2); // true

Map<String, Object> person3 = new HashMap<>();
person3.put("name", "");
person3.put("age", null);
mapSchema.isValid(person3); // false

Map<String, Object> person4 = new HashMap<>();
person4.put("name", "Wemby");
person4.put("age", -3);
mapSchema.isValid(person4); // false
```
