# SunPanelsApplication
Last assignment in Lernias education to become a systemdeveloper. Thesis work. 

A program that makes it easier to calculate your profit from selling sun-energi from sunpanels.

(remake from an earlier made, small program, that uses scanner input. See: https://github.com/clindforsSejsing/Java_small_programs/blob/main/Java_ltu/sunpanels.java )


#API DOCS
-----
POST:
localhost:8080/api/person/add

(ex)
{
"email":"Kalle_anka@Anka.se"
}

Response 201 CREATED:
{
"id": 1,
"email": "Kalle_anka@Anka.se"
}
------
GET:
localhost:8080/api/person/{personId}

(ex) localhost:8080/api/person/1

Response 200 OK:
{
"id": 1,
"email": "Kalle_anka@Anka.se"
}
-------
GET:
localhost:8080/api/person/all

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
POST:
localhost:8080/api/sunpanel/add

(ex)
{
"amountOfPanels":23,
"sekPerKwh": 2.62
}

Response 201 CREATED:
{
"id": 1,
"areaOfPanels": 39.1,
"sekPerKwh": 2.62,
"amountOfPanels": 23,
"sunpanelnotes": null
}

----
GET:
localhost:8080/api/sunpanel/all

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

GET:
localhost:8080/api/sunpanel/{sunpanelId}
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
PATCH:
localhost:8080/api/sunpanel/{sunpanelId}

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
POST:
localhost:8080/api/note/person/{personId}/sunpanel/{sunpanelId}

(ex) localhost:8080/api/note/person/1/sunpanel/1

{
"sunrise": "2022-12-05T02:13:16",
"sunset": "2022-12-05T23:13:16"
}

Response 201 CREATED:
{
"id": 1,
"sunrise": "2022-12-05T02:13:16",
"sunset": "2022-12-05T23:13:16",
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

----
GET notes for one person:
localhost:8080/api/note/person/{personId}

(ex) localhost:8080/api/note/person/1

Response 200 OK:
[
{
"id": 1,
"sunrise": "2022-12-05T02:13:16",
"sunset": "2022-12-05T23:13:16",
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
GET a note by notes id :
localhost:8080/api/note/{noteid}

(ex) localhost:8080/api/note/2

Response 200 OK:
{
"id": 2,
"sunrise": "2022-12-05T02:13:16",
"sunset": "2022-12-05T23:13:16",
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
DELETE a note by note id:
localhost:8080/api/note/{noteid}/remove

(ex)localhost:8080/api/note/1/remove

Response 200 OK: {returns nothing}


----
