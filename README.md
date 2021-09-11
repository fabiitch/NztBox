### Simple 3D collision systems

Put [NztGdx](https://github.com/fabiitch/NztGdx) in same folder or change path in settings.gradle

## What is this ?
NztBox is a simple 3D collision world.
Its really similar to Box2D for data type and usage. (Body, Fixture, ContactListener)

The volume are only 2D shape (Circle, Polygons) and you give a height.

But its just a collision engine, after collision a collision forces are computed but its very simplist
Its not realistic.

---
## BodyType :

### 1) Static 
    Cant move or rotate
    Gravity has no effect
    Collides with Dynamic

### 2) Dynamic
    Can move or rotate.
    Gravity has effects
    Collides with Static, Dynamic, Kinematic
### 3) Kinematic
    Can move or rotate
    Gravity has no effect
    Collides with Static, Dynamic, Kinematic
### 4) Ghost
    Can move or rotate
    Gravity has no effect
    Collides with all but dont generate response and forces
---
## Fixtures
    You can attache one or many fixtures to a body.
    The fixture contains the shape and his height and Zpos (relative to body)
    The shapes intersection creates contacts
---
## Shapes

### 1) Circle
### 2) Rectangle
    cant rotate (based on LibGdx Rectangle
### 3) Polygons
    only convex
### 4) Ray? //TODO

## Collisions Event :
you have to set world.contactListener
### 1) preSolve(ContactFixture contactFixture);
    Called when intersectors find contact between two fixtures
    you can modify contact params
    public boolean ignoreContact = false; //contact is remove, endContact not called
    public boolean noImpact = false;      //no forces are apply
    public boolean enableContact = true;    
    public boolean tickEveryStep = false;

### 2) beginContact(ContactFixture contactFixture);
    Forces and rebound are computed here, but not applied
    You can modify it in contactFixture.collisionData
### 3) continueContact(ContactFixture contactFixture);
### 4) endContact(ContactFixture contactFixture);
---
## Filters contact :

	For eclipse : 
	Java Build Path
	1) Project add NztGdx	
	2) Order en export -> put NztGdx after src/test/resources
	3) Projects -> NztGdx Visible onlu for test sources : No
	Without test codde : No
---
## Data :

* Body: Global Entity who contains all shapes
* Fixture : One shape of a body
* UserData : Your object inside Fixture or Body
* ContactBody : ContactBetween two Body
* ContactFixtures : ContactBetween two fixture
---
## Query World


