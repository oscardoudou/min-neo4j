Cypher
==============
basic format of Cypher read statement
```
MATCH <pattern>
WHERE <conditions>
RETURN <expression>
```

### Overview
[Cypher](https://neo4j.com/developer/cypher-basics-i/) is a language for describing visual pattern in graphs using ASCII-Art syntax. 
```
match (movie:Movie) 
optional match (director:Person{name: 'Tom Hanks'})-[:DIRECTED]->(movie),(director)-[:ACTED_IN]->(movie)
return movie.title, director.name
```
```
match (movie:Movie) 
optional match (director:Person|Actor)-[:DIRECTED]->(movie)<-[:ACTED_IN]-(director)
return movie.title, director.name
```
Node, relationship, pattern are key componenet of the query.

Put a node inside parenthesis `()`, relationship in square bracket`[]` along with `-` on both side(you could specify direction by attaching `<` or` >`), property in curly bracket `{}`. 

Variable use to simply representing node or relationship and later use it to reference later. Naming convention: variable camelCase,relationship UPPER_CASE

Node could have multiple labels while relationship could have only one type, both are specified after colon. For node, mutliple labels could be specified using pipe `|`.

Keywords
For read: match
For write: create/merge/delete remove/set 
other: where, with, return
logic: and or not in

Difference between create/delete and set/remove:
set/remove are used to update properties and label, while create delete operate on higher level(node and relationship)


 