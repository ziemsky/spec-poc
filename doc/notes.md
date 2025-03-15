# Possible payloads returned from the endpoint

A couple of possible options:

## Basic
...where all predicates at the top level are evaluated using 'AND' boolean operation:
```json
{
    "specifications": [
        {
            "id": 1,
            "name": "Ensures feature A and B are enabled for modelA and modelB sold in India.",
            "predicates": [
                { "param": "COUNTRY", "match": "EQUALS", "value": "IND" },
                { "param": "MODEL",   "match": "REGEX",  "value": "modelA|modelB" }
            ],
            "configs": [
                { "fieldA": "valueA" },
                { "fieldB": "valueB" }
            ]
        },
        {
            "id": 2,
            "name": "Some other rule.",
            ...
        }
    ]
}
```

See [PostgreSQL](../docker-compose.yml)-compatible [schema.sql](../src/main/resources/schema.sql) for an example of schema to supporting this variant.  

An example of the possible management GUI:

![Management GUI for the 'basic' type of specification](Management%20GUI%20mockup.excalidraw.png "Management GUI for the 'basic' type of specification")


## Elaborate
...where predicates are evaluated using operators explicitly specified in the payload.

Note that in this case, `predicates` always needs an operator as a top-level object.

Custom operators could cover operations such as 'not', 'in' and more, i.e. not be limited to just boolean operations.

```json
{
    "specifications": [
        {
            "id": 1,
            "name": "Modifies config for modelA and modelB sold in India.",
            "predicates": {
                "operator" : "and",
                "operands": [
                    { "param": "COUNTRY", "match": "EQUALS", "value": "IND" },
                    {
                        "operator" : "or",
                        "operands": [
                            { "param": "MODEL", "match": "EQUALS", "value": "modelA" },
                            { "param": "MODEL", "match": "EQUALS", "value": "modelB" }
                        ]
                    }
                ]
            },
            "configs": [
                { "fieldA": "valueA" },
                { "fieldB": "valueB" }
            ]
        },
        {
            "id": 2,
            "name": "Some other rule.",
            ...
        }
    ]
}
```