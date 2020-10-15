**Introduction**
This change made by Sanket
This is a parking lot management system developed in SpringBoot. It provide endpoints for setup a parking lot containing different number of levels. Number of levels and capacity of each level can be edited by the administrator. It also provide statistics based on vehicles and date.

**Requirements**

1. Install Open JDK-15
2. MYSQL server
3. Setup a Maven 4.0.0 project

**Running the Application**

Application contains VehicleParkerApplication.java file . This file contains the main method. When this file is run, end points are activated. Then requests are send to the endpoint. Endpoint provide responses corresponding to the request.

**Application Structure**

Application mainly contain 4 services .
- Parking Service : It allow parking and unparking of vehicles
- Level Service : It allow to add, edit and delete levels . 
- Vehicle Service : It allow to add, edit and delete levels. 
- Statistics Service : It allow to get statistics based on the date and vehicle types.

**Endpoint Input and Output**

**Parking service** : It provide 4 end points .
1.To get the available space in all levels.

- URL : /getAvailableSpace 
- Request Method : GET
- Input type: None
- Output type: application/json
- Output Fields (List of following object type)
	- levelnumber : Integer
	- availabeSpace : Dictionary 
		- key : String (Vehicle name)
		- value : Integer (Number of slots available)
		- Eg : {"car", 2}
	- Eg : {
			{levelNumber : 0, availableSpace : {"car":2, "bike",3} } , 
			 {levelNumber : 1, availableSpace : {"car":10, "bike",15} } 
			}

2.To get the parked vehicles in all levels

- URL : /getParkedVehicles 
- Request Method : GET
- Input type: None
- Output type: application/json
- Output Fields (List of following object type)
	- id : Integer (unique)
	- levelNumber : integer
	- vehicleType : integer (unique for a vehicle type)
	- vehicleName : String 
	- vehicleNumber : String (unique)
	- date : LocalDate(yyyy-mm-dd)
	- time : LocalTime (hh:mm:ss)
	- Eg : {
	    "id": 38,
	    "levelNumber": 0,
	    "vehicleType": 6,
	    "vehicleName": "container",
	    "vehicleNumber": "KL 11 AC 5987", 
	     "date": "2020-10-07", 
		"time": "15:43:07" },
		
3.To park a vehicle

- URL : /park
- Request Method : POST
- Input type: application/json 
- Input Fields
	- vehicleName : String
	- vehiclenumber : String
		Eg : {
		"vehicleName":"car", 
		"vehicleNumber":"KL 11 BC 5978"
		}
- Output type: application/json
- Output Fields 
	- message : String
	- vehicleMap : Object 
		- id : Integer (unique)
		- levelNumber : integer
		- vehicleType : integer (unique for a vehicle type)
		- vehicleName : String 
		- vehicleNumber : String (unique)
		- date : LocalDate(yyyy-mm-dd)
		- time : LocalTime (hh:mm:ss)
	- isSuccess : Boolean
	- Eg : { 
				"message" : "vehicle parked",
				{
				    "id": 38,
				    "levelNumber": 0,
				    "vehicleType": 6,
				    "vehicleName": "container",
				    "vehicleNumber": "KL 11 AC 5987", 
				     "date": "2020-10-07", 
					"time": "15:43:07"
				 },
				"success":true
			}
4.To unpark a vehicle

- URL : /unpark
- Request Method : POST
- Input type: application/json 
- Input Field
	- id : Integer (unique) [Id received when the vehicle was parked]
	- Eg : {
				"id":47
			}
- Output Type : application/json
- Output Fields 
	- message : String
	- vehicleMap : Object 
		- id : Integer (unique)
		- levelNumber : integer
		- vehicleType : integer (unique for a vehicle type)
		- vehicleName : String 
		- vehicleNumber : String (unique)
		- date : LocalDate(yyyy-mm-dd)
		- time : LocalTime (hh:mm:ss)
	- isSuccess : Boolean
	- Eg : { 
				"message" : "vehicle unparked",
				{
				    "id": 38,
				    "levelNumber": 0,
				    "vehicleType": 6,
				    "vehicleName": "container",
				    "vehicleNumber": "KL 11 AC 5987", 
				     "date": "2020-10-07", 
					"time": "15:43:07"
				 },
				"success":true
			}
			
**Level service :** It provide 4 services

1.To get all the levels 

- URL : /levels
- Request Method : GET
- Input type: non
- Output Type : application/json
- Output Fields (List of following objects)
	-levelNumber : integer
    -allowedVehicles : List of objects
     	- "id": integer
     	- "vehicle": object
     	- "id": integer
     	- "name": String
     	- "parkingRate": integer
     	- "occupiedSlots": integer
     	- "freeSlots": integer
     	- "max_SLOTS": integer 
    - Eg: 
    -[
            {
             "levelNumber": 0,
             "allowedVehicles": [
                 {
                     "id": 8,
                     "vehicle": {
                         "id": 6,
                         "name": "container",
                         "parkingRate": 100
                     },
                     "occupiedSlots": 1,
                     "freeSlots": 2,
                     "max_SLOTS": 3
                 },
                 {
                     "id": 9,
                     "vehicle": {
                         "id": 5,
                         "name": "truck",
                         "parkingRate": 70
                     },
                     "occupiedSlots": 0,
                     "freeSlots": 10,
                     "max_SLOTS": 10
                 }
             ]
         },
     ]
     
2.To add a level

- URL : /levels
- Request Method : POST
- Input type: application/json
- Input Fields : Level object
	- levelNumber : integer
	- allowedVehicles : List of objects
		- vehicle : Vehicle object
			- name : string
		- occupiedSlots : integer
		- max_SLOTS : integer
	- Eg :
		{
			"levelNumber": 8,
		        "allowedVehicles": [
		            {
		                "vehicle": {
		                        "name": "bike"
		                },
		                "occupiedSlots": 0,
		                "max_SLOTS": 15
		            }
		        ]
		}
- Output Type : application/json
- Output Fields
	- message : string
	- level : object (Level objecr given as input is returned back ifthe operation is success)

3.To edit a level

- URL : /levels
- Request Method : PUT
- Input type: application/json
- Input Fields : Level object
	- levelNumber : integer
	- allowedVehicles : List of objects
		- vehicle : Vehicle object
			- name : string
		- occupiedSlots : integer
		- max_SLOTS : integer
	- Eg :
		{
			"levelNumber": 8,
		        "allowedVehicles": [
		            {
		                "vehicle": {
		                        "name": "bike"
		                },
		                "occupiedSlots": 0,
		                "max_SLOTS": 15
		            }
		        ]
		}
- Output Type : application/json
- Output Fields
	- message : string
	- level : object (Level object given as input is returned back if the operation is success)

4.To delete a level

- URL : /levels
- Request Method : DELETE
- Input type: application/json
- Input Fields : 
	- levelNumber : integer
- OutputType : application/json
- Output Fields :
	- message : string
	- level : null
	- isSuccess : Boolean
	- Eg :
			{
			    "message": "Level deleted",
			    "level": null,
			    "succes": true
			}
			
**Vehicle Service :** It provide 4 end points

1.To get all vehicles

- URL : /vehicles
- Request Method : GET
- Input type: none
- OutputType : application/json
- Output Fields : list of vehicle object
	- id : integer
	- name : string
	- parkingRate : integer
	- Eg : [
			   {
			        "id": 1,
			        "name": "car",
			        "parkingRate": 20
			    },
			    {
			        "id": 2,
			        "name": "bus",
			        "parkingRate": 40
			    },
			]

2.To add a vehicle

- URL : /vehicles
- Request Method : POST
- Input type: application/json
- Input Fields : vehicle object
	- name : string
	- parkingRate : integer
	- Eg : {
				"name":"jeep",
				"parkingRate":25
			}
- OutputType : application/json
- Output Fields : vehicleResponse object
	- message : string
	- vehicle : vehicle object given as input is returned back if success else null is returned
	- isSuccess : Boolean
	- Eg : {
			    "message": "vehicle added",
			    "vehicle": {
			        "id": 49,
			        "name": "jeep",
			        "parkingRate": 25
			    },
			    "succes": true
			}

3.To edit a vehicle

- URL : /vehicles
- Request Method : PUT
- Input type: application/json
- Input Fields : vehicle object
	- name : string
	- parkingRate : integer
	- Eg : {
				"name":"jeep",
				"parkingRate":25
			}
- OutputType : application/json
- Output Fields : vehicleResponse object
	- message : string
	- vehicle : vehicle object given as input is returned back if success else null is returned
	- isSuccess : Boolean
	- Eg : {
			    "message": "vehicle edited",
			    "vehicle": {
			        "id": 49,
			        "name": "jeep",
			        "parkingRate": 25
			    },
			    "succes": true
			}

4.To delete a vehicle

- URL : /vehicles
- Request Method : DELETE
- Input type: application/json
- Input Fields : vehicle object with name field only
	- name : string
	- Eg : {
				"name":"jeep",
			}
- OutputType : application/json
- Output Fields : vehicleResponse object
	- message : string
	- vehicle : vehicle object given as input is returned back if success else null is returned
	- isSuccess : Boolean
	- Eg : {
			    "message": "vehicle deleted",
			    "vehicle": {
			        "id": 49,
			        "name": "jeep",
			        "parkingRate": 25
			    },
			    "succes": true
			}
			
				 
**Statistics Service :** It provide one point

1.To get the statistics

- URL : /statistics
- Request Method : DELETE
- Input type: none
- OutputType : application/json
- Output Fields : List of statistics object
	- localDate : Date
	- vehicleStatByTypeList :List of objects
		- count : int
		- totalAmount : int
		- vehicle : vehicle object
	- Eg : [
			    {
			        "localDate": "2020-10-07",
			        "vehicleStatByTypeList": [
			            {
			                "count": 1,
			                "totalAmount": 100,
			                "vehicle": {
			                    "id": 6,
			                    "name": "container",
			                    "parkingRate": 100
			                }
			            },
			            {
			                "count": 2,
			                "totalAmount": 40,
			                "vehicle": {
			                    "id": 1,
			                    "name": "car",
			                    "parkingRate": 20
			                }
			            },
			            {
			                "count": 0,
			                "totalAmount": 0,
			                "vehicle": {
			                    "id": 2,
			                    "name": "bus",
			                    "parkingRate": 40
			                }
			            },
			            {
			                "count": 0,
			                "totalAmount": 0,
			                "vehicle": {
			                    "id": 3,
			                    "name": "van",
			                    "parkingRate": 20
			                }
			            },
			            {
			                "count": 0,
			                "totalAmount": 0,
			                "vehicle": {
			                    "id": 4,
			                    "name": "bike",
			                    "parkingRate": 10
			                }
			            },
			            {
			                "count": 0,
			                "totalAmount": 0,
			                "vehicle": {
			                    "id": 5,
			                    "name": "truck",
			                    "parkingRate": 70
			                }
			            }
			        ]
			    }
			]

