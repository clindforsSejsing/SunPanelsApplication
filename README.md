# SunPanelsApplication
Last assignment in Lernias education to become a systemdeveloper. Thesis work. 

A program that makes it easier to calculate your profit from selling sun-energi from sunpanels.

(remake from an earlier made, small program, that uses scanner input. See: https://github.com/clindforsSejsing/Java_small_programs/blob/main/Java_ltu/sunpanels.java )


#API DOCS
-----
POST:localhost:8080/api/person/register

(ex){"name":"Kalle Anka",
"email":"kalle@ankamail.com",
"password":"secret_password"}Response 200 OK:
{
"id": 1,
"email": "kalle@ankamail.com",
"name": "Kalle Anka"
}

(ex){
"email":"KalleankaAnka.se"
}

Response 406 Not Acceptable:

------
GET:localhost:8080/api/person/login

(ex) {"email":"kalle@ankamail.com", "password":"secret_password"}
Response 200 OK {login ok}:

{
"id": 1,
"email": "Kalle_anka@Anka.se"
}
if user not exists: 
Response 400 Bad Request{Expected email not found}

-------
GET:localhost:8080/api/person/all

Response 200 OK:

[
{
"id": 1,
"email": "Kalle_anka@Anka.se"
},
{
"id": 2,
"email": "Kajsa_anka@Anka.se"
}
]

----
GET: localhost:8080/api/person/user
(get logged in user)

Response 200 OK{
"id": 1,
"email": "kalle@ankamail.com",
"name": "Kalle Anka"
}
if no user is logged in: 
Response 401 Unauthorized {Unauthenticated}

-------
POST: GET: localhost:8080/api/person/logout
 Response 200 OK {Logout ok}

------

POST:localhost:8080/api/sunpanel/add

(ex){"amountOfPanels":23,"sekPerKwh": 2.62}

Response 201 CREATED:

{
"id": 1,
"areaOfPanels": 39.1,
"sekPerKwh": 2.62,
"amountOfPanels": 23,
"sunpanelnotes": null
}

----
GET:localhost:8080/api/sunpanel/all

Response 200 OK:

[
{
"id": 1,
"areaOfPanels": 39.1,
"sekPerKwh": 2.62,
"amountOfPanels": 23,
"sunpanelnotes": []
},
{
"id": 2,
"areaOfPanels": 17.0,
"sekPerKwh": 3.0,
"amountOfPanels": 10,
"sunpanelnotes": []
}
]

----

GET:localhost:8080/api/sunpanel/{sunpanelId}

(ex) localhost:8080/api/sunpanel/2

Response 200 OK:

{
"id": 2,
"areaOfPanels": 17.0,
"sekPerKwh": 3.0,
"amountOfPanels": 10,
"sunpanelnotes": []
}

----
PATCH:localhost:8080/api/sunpanel/{sunpanelId}

(ex) localhost:8080/api/sunpanel/1

{
"amountOfPanels":100,
"sekPerKwh": 2.69
}

Response 200 OK:

{
"id": 1,
"areaOfPanels": 170.0,
"sekPerKwh": 2.69,
"amountOfPanels": 100,
"sunpanelnotes": null
}

-----------
POST:localhost:8080/api/note/person/{personId}/sunpanel/{sunpanelId}

(ex) localhost:8080/api/note/person/1/sunpanel/1

{
"sunrise": "2022-12-06T02:13:16",
"sunset": "2022-12-06T23:13:16"
}

Response 201 CREATED:

{
"id": 1,
"sunrise": "2022-12-06T02:13:16",
"sunset": "2022-12-06T23:13:16",
"incomeSek": 71.42,
"nmbOfSunHours": 21,
"person": {
"id": 1,
"email": "Kalle_anka@Anka.se"
},
"sunpanel": {
"id": 1,
"areaOfPanels": 39.1,
"sekPerKwh": 2.62,
"amountOfPanels": 23
}
}

(ex){
"sunrise": "2022-12-08T02:13:16",
"sunset": "2022-12-08T23:13:16"
}

Response 400 Bad Request {Wrong format on date, needs to be in june or july, try again}

----
GET notes for one person:localhost:8080/api/note/person/{personId}

(ex) localhost:8080/api/note/person/1

Response 200 OK:

[
{
"id": 1,
"sunrise": "2022-12-06T02:13:16",
"sunset": "2022-12-06T23:13:16",
"incomeSek": 71.42,
"nmbOfSunHours": 21,
"person": {
"id": 1,
"email": "Kalle_anka@Anka.se"
},
"sunpanel": {
"id": 1,
"areaOfPanels": 39.1,
"sekPerKwh": 2.62,
"amountOfPanels": 23
}
}
]

-------
GET a note by notes id :localhost:8080/api/note/{noteid}

(ex) localhost:8080/api/note/2

Response 200 OK:

{
"id": 2,
"sunrise": "2022-12-06T02:13:16",
"sunset": "2022-12-06T23:13:16",
"incomeSek": 71.42,
"nmbOfSunHours": 21,
"person": {
"id": 1,
"email": "Kajsa_anka@Anka.se"
},
"sunpanel": {
"id": 1,
"areaOfPanels": 39.1,
"sekPerKwh": 2.62,
"amountOfPanels": 23
}
}

-----
DELETE a note by note id:localhost:8080/api/note/{noteid}/remove

(ex)localhost:8080/api/note/1/remove

Response 200 OK: {returns nothing}

if note with id not found: 

Response 500 Internar Server Error {NoteNotFoundException: note with id {id} does not exist}

if delete note failed: 

Response 503: Service Unavailable

----
