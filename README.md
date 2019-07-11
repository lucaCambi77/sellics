# Sellics Assignment

#### 1. What assumptions did you make?

As described in the assignment, Amazon completion API does not return information about keyword search volume. 
but it returns 10 suggestions for each prefix input sorted by search volume. Main assumption is that user is lazily typing an input in the search box and expecting to find what he is looking for within 10 suggestions. So he hopes to get a hint as soon as possible to refine is search.

I decided also to use https://completion.amazon.co.uk/api/2017/suggestions as reverse engineering of a browser network traffic that shows also a model  possibly related to the search itself.

#### 2. How does your algorithm work?

1. User provides a keyword
2. As we are reverse engineering from a trie, service creates a list of substrings for specified keyword starting from the 1st letter. 
3. Service creates a future list of api calls and performs requests to Amazon completion API
4. This serial operation is wrapped in `Observable` to make possible control duration of processing and break it at some time point
5. From response the suggested value is retrieved and compared with initial keyword. Is the whole keyword present in suggestion or not.
6. Score is calculated by dividing the number of matches in suggestion with the total number of suggestions. In this case
total `number of suggestions` is calculated by multiplying `number of performed requests` to `10`.   

#### 3. Do you think the (*hint) that we gave you earlier is correct and if so - why?

Yes i think at least it is difficult to say that such order makes sense and also users can see all 10 suggestions at a glance

#### 4. How precise do you think your outcome is and why?

I think it is a good starting point. There are other factors in a search, for example mispelled words and permutations of words could be considered

## Project description

To build project 

`$ ./mvn clean install`

To run 

`$ java -jar target/*.jar`



