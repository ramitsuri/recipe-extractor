## Recipe Extractor 

<p align="center">
    A JVM library written in Kotlin to extract recipes from website that present their data in Json-LD (Linked Data) 
    format (https://json-ld.org/)
</p>

### From
![image](images/From.png)

### To
![image](images/To.png)

### Usage
- Build the project using Gradle
- Import library in your project
```
val url = "https://www.cookwithmanali.com/tawa-paneer/"
val extractor = RecipeExtractor();
val recipe = extractor.get(url);
```
- On run the REST server app and retrieve recipe like
http://localhost:8080/api/recipe/extract?recipe=https://www.cookwithmanali.com/tawa-paneer/

### Supported websites 
These websites have been tested to work for the most part

- https://www.bonappetit.com
- https://www.cookwithmanali.com
- https://www.bbc.co.uk
