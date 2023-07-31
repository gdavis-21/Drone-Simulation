CS420
FALL 2022
Team Barishal


The design contains the implementation for the farm dashboard. The implementation:
Implements singleton and composite design patterns
Displays for visualization of the farm
Animations for the drone to visit the featured items
Implements dashboard using JavaFX

Active Bugs
1.) [11/04/22] When Drone is scanning farm, user can click go to home in the middle of the process.
Drone completes scan farm before going home.

Fixed Bugs
1.) [11/04/22] Add @ in front of drone pathway to open in scene builder

Requirement
JavaFX Version 19 - https://openjfx.io/
JDK 18 or higher - https://www.oracle.com/java/technologies/downloads/#jdk19-windows
Eclipse 2022 or higher - https://www.eclipse.org/ide/
Scene Builder - https://gluonhq.com/products/scene-builder/
GitHub - https://github.com/gdavis-21/Pt-1-Barishal-CS420.git    
Install Dependencies
Refer to the setup youtube links in the reference section below for Eclipse and JavaFX
Reference to JavaFx Eclipse Scene Builder V2.0 setup guide for step by step installation assistance
Execute the MainApp.java file in the Eclipse IDE
Project Checklist
UML class diagrams for design patterns were implemented by various classes and methods
Visualization of element for singleton class
Visualization of elements for composition patterns
Animate drone in flight


Features
Farm Dashboard
Add barn to root
Add livestock-area (item-container) to the barn
Add cow (item) to the livestock-area
Add milk storage (item) to the barn
Add storage building (item-container) to the root
Add tractor (item) to the storage building
Add tiller (item) to the storage building
Add command center (item-container) to root
Add drone (item) to command center
Add soy crop (item)
Visualize (creates a visualization/plot of all items and item containers in the collection)


References
https://www.youtube.com/watch?v=bC4XB6JAaoU  (JavaFX with Eclipse Setup)
https://www.youtube.com/watch?v=yngO5WwfZCY  (JavaFX with Eclipse Setup)
https://www.youtube.com/watch?v=UdGiuDDi7Rg  (JavaFX Animations)
