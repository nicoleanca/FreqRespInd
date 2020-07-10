To run the web application:

In order to run this on your computer you will need to install java and an IDE
 such as eclipse or IntelliJ.

For using IntelliJ:

Download IntelliJ community for free
Open it and select “open” from the menu (under new project) and
  open the freqrespind folder
This should take a moment to load
Then open src main java project Nicole freqrespind and FreqrespindApplication.java
Hover mouse over FreqrespindApplication.java and right click -
  there should be a command to Run the application as FreqrespindApplication.java
Press Run
It should show on the lower part of the screen in the Console that the app was
  built (might take a few seconds)
Then open a browser and type localhost:8080
The website should open and work properly! Enjoy :)

Using eclipse should be similar, look for the run button (green play button)
  on the top bar (could be next to a green bug)


To add more tissues:

- go to src main java project Nicole freqrespind debye_input_data
- make new file called newId_DebyeParam.txt in the same format:
  first line: #name of new tissue
  second line: the four initial Debye parameters separated by single spaces
- go to src main resources static
- in index.html around line 23 in the enumeration of the tissues add the new
  tissue in the same format:
  <option value="newId"> name of new tissue </option>
