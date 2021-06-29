# Java_Cinema_Project

if you get to many errors while running the project watch the videos in video folder.
it may be because of setting JavaFx SDK and VM option so the videos may help you.



Intellij idea JavaFx VM Options:
Click on Run in toolbar > Edit Configurations then copy the text below and past it in VM options
text box then click apply then ok. Now you can run the project.
if VM options text box is now showing click on Modify Options > Add VM options and now you copy the
text there.

--module-path ".\src\libs\javaFx\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml




Eclipse JavaFx VM Options:
Right Click on project folder > Run as > Run Configurations > click on arguments tab
then copy the text below and past it in VM arguments text box then click on apply
now you can run the project.

--module-path ".\src\libs\javaFx\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml




Important note: The Working directory in Run Configuration should be "your path\cinemaProject"
so that project can work otherwise it would not be able to read and write data which will
cause the project to crash.


Disclaimer: this is just a hobby project for non-commercial use only none of these icons or images belongs to me I just found them throw the internet. Data (names, emails, phone numbers) used is this application is just some dummy (sample) data. 
