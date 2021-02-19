# jacksonxml-lists-bug

When parsing an XML string containing repeated nested elements such as

```
<a>
    <b>1</b>
    <b>2</b>
    <b>3</b>
</a>
```

with 

```
.unmarshal().jacksonxml()
```

the resulting body only has the last `<b>` element.

This is a known bug in `jackson-databind-xml` which was fixed in version 2.12.0
However, Camel 3.8.1 still uses 2.11.4 and the latest supported version for FUSE still uses 2.9.10

I created a simple unit test that demonstrates this bug in both the latest community and FUSE versions. https://github.com/herbertkb/jacksonxml-lists-bug

Simply `cd` into either and run `mvn clean test`. In the logs you can see:

```
INFO  Pre unmarshal: <root><list><item>ONE</item><item>TWO</item><item>THREE</item></list></root>
INFO  Post unmarshal: {list={item=THREE}}
```
