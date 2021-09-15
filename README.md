# NztBox <img src="https://github.com/fabiitch/NztBox/blob/master/src/test/resources/box.png?raw=true" alt="Logo" width="150"/>
    - Simple 3D collision systems using in LibGdx
    - Can be used as 2D
    - Only Java

## What is this ?
NztBox is a simple 3D collision world.
Its really similar to Box2D for data type and usage. (Body, Fixture, ContactListener)

The volume are only 2D shape (Circle, Polygons) and you give a height.

But its just a collision engine, after collision a collision forces are computed but its very simplist
Its not realistic.

---
## BodyType :

### 1) Static 
    - Cant move or rotate
    - Gravity has no effect
    - Collision event with Dynamic, Ghost
    - Rebound with Dynamic
    - Forces with Dynamic

### 2) Dynamic
    - Can move or rotate
    - Gravity has effect
    - Collision event with Static, Dynamic, Kinematic, Forces
    - Rebound with Static, Dynamic, Kinematic
    - Forces with Static, Dynamic, Kinematic, Forces
### 3) Kinematic
    - Can move or rotate
    - Gravity has effect
    - Collision event with Dynamic, Kinematic, Ghost, Forces
    - Rebound with Dynamic, Kinematic
    - Forces with Dynamic, Kinematic, Forces
### 4) Ghost
    - Can move or rotate
    - Gravity has no effect
    - Collision event with Static, Dynamic, Kinematic, Ghost, Forces
    - No rebound
    - No forces
### 5) Force
    - Can move or rotate
    - Gravity has no effect
    - Collision event with Dynamic, Kinematic, Ghost, Forces
    - No rebound
    - Forces with Dynamic
### 6) Laser
    - Can move or rotate
    - Gravity has no effect
    - Collision event with Dynamic, Kinematic, Ghost, Forces
    - Rebound
    - No Forces
---
## Fixtures
    You can attache one or many fixtures to a body.
    The fixture contains the shape and his height and Zpos (relative to body)
    The shapes intersection creates contacts
---
## Shapes
    - Circle
    - Rectangle (cant rotate based on LibGdx Rectangle)
    - Polygons only convex
    - Ray? //TODO
---
## Contacts :
    Generate a contact Fixture.
    A contact can be blocking or not, he can apply forces on bodies or not.
    You can modify contact response with attributs of ContactFixture
    ContactFixture.collisionData contains 
---
## Contacts Event :
    - You have to set world.contactListener to get notified by contacts between body's fixtures
    - Contact can be blocking (shape cant cross other) and generate rebound
    - Contact can generate impact's forces
### 1) preSolve
    Called when intersectors find contact between two fixtures
    you can modify contact params
    public boolean ignoreContact = false; //contact is remove, endContact not called
    public boolean noImpact = false;      //no forces are apply
    public boolean enableContact = true;    
    public boolean tickEveryStep = false;
### 2) beginContact
    Forces and rebound are computed here, but not applied
    You can modify it in contactFixture.collisionData
### 3) continueContact
    Called every step if ContactFixture.continueContact
### 4) endContact

---
## Filters contact :


---
## Data :

* Body: Global Entity who contains all shapes
* Fixture : One shape of a body
* UserData : Your object inside Fixture or Body
* ContactBody : ContactBetween two Body
* ContactFixtures : ContactBetween two fixture
---
## Query World

## Install
    Put [NztGdx](https://github.com/fabiitch/NztGdx) in same folder or change path in settings.gradle
	
    For eclipse : 
	Java Build Path
	1) Project add NztGdx	
	2) Order en export -> put NztGdx after src/test/resources
	3) Projects -> NztGdx Visible onlu for test sources : No
	Without test codde : No
