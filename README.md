# Spaces Service

Demo service relation many to many.

## Feature
- Store Rocket
- Get List Rocket
- Store Asteroid
- Get List Asteroid
- Display Triangle

## Payload
**Store Rocket**

Method: POST

Url: http://localhost:8005/spaces/v1/rocket/store

Request: ```
{
    "name": "SpaceX",
    "fuelTank": 2000,
    "astronoutCap": 10,
    "listAsteroid": [
        {
        "name": "Astra1",
        "minDiameterKm": 10000.6,
        "maxDiameterKm": 132141243.3,
        "isHazardous": true
        },
        {
        "name": "Astra2",
        "minDiameterKm": 112111.6,
        "maxDiameterKm": 213212.3,
        "isHazardous": true
        }
    ]
} ```

Response: ``` {
    "traceId": "68d65ddc7b736e0b53c8f9644a8a4822",
    "data": {
        "rocketName": "SpaceX",
        "rocketFuelTank": 2000,
        "astronoutCap": 10,
        "listAsteroid": [
            {
                "id": 1,
                "name": "Astra1",
                "minDiameterKm": 10000.6,
                "maxDiameterKm": 1.321412433E8,
                "rockets": null,
                "hazardous": false
            },
            {
                "id": 2,
                "name": "Astra2",
                "minDiameterKm": 112111.6,
                "maxDiameterKm": 213212.3,
                "rockets": null,
                "hazardous": false
            }
        ]
    },
    "response_code": "00",
    "response_message": "Success"
} ```

<br/>

**Get List Rocket**

Method: GET

Url: http://localhost:8005/spaces/v1/rocket/list

Request Param: ``` {
"page": 0,
"size": "10"
} ```

Response: ``` {
    "traceId": "68d65f8b8fdae564bf01b2c781e7baf3",
    "data": {
    "list": [
        {
            "rocketName": "SpaceX",
            "rocketFuelTank": 2000,
            "astronoutCap": 10,
            "listAsteroid": [
                {
                    "id": 1,
                    "name": "Astra1",
                    "minDiameterKm": 10000.6,
                    "maxDiameterKm": 1.321412433E8,
                    "hazardous": false
                },
                {
                    "id": 2,
                    "name": "Astra2",
                    "minDiameterKm": 112111.6,
                    "maxDiameterKm": 213212.3,
                    "hazardous": false
                }
            ]
        }
    ],
    "totalPage": 1,
    "totalSize": 1
    },
    "response_code": "00",
    "response_message": "Success"
} ```

<br/>

**Store Asteroid**

Method: POST

Url: http://localhost:8005/spaces/v1/asteroid/store

Request: ``` {
"name": "JupiterX",
"minDiameterKm": 6666.6,
"maxDiameterKm": 99999.3,
"isHazardous": true
} ```

Response: ``` {
"traceId": "68d65fd1a8f151d2b079a2e4ce0459be",
"data": {
"asteroidName": "JupiterX",
"minDiameterKm": 6666.6,
"maxDiameterKm": 99999.3,
"hazardous": false
},
"response_code": "00",
"response_message": "Success"
} ```

<br/>

**Get List Asteroid**

Method: GET

Url: http://localhost:8005/spaces/v1/asteroid/list

Request Param: ``` {
"page": 0,
"size": "10"
} ```

Response: ``` {
"traceId": "68d65fe4b4cfdc26ef4e94ba87946b8d",
"data": {
"list": [
{
"asteroidName": "JupiterX",
"minDiameterKm": 6666.6,
"maxDiameterKm": 99999.3,
"hazardous": false
},
{
"asteroidName": "Astra2",
"minDiameterKm": 112111.6,
"maxDiameterKm": 213212.3,
"hazardous": false
},
{
"asteroidName": "Astra1",
"minDiameterKm": 10000.6,
"maxDiameterKm": 1.321412433E8,
"hazardous": false
}
],
"totalPage": 1,
"totalSize": 3
},
"response_code": "00",
"response_message": "Success"
} ```

<br/>

**Display Result**

Method: GET

Url: http://localhost:8005/spaces/v1/astronout/display

Request Param: ``` {
"type": "NORMAL"
} ``` or ``` {
"type": "PASCAL"
} ```

Response Normal: <br>
1<br>
1 2<br>
1 2 3<br>
1 2 3 4<br>
1 2 3 4 5

Response Pascal: <br>
1<br>
1 1<br>
1 2 1<br>
1 3 3 1<br>
1 4 6 4 1

<br/>

