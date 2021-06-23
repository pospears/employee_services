<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Employee Search Exercise</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@mdi/font@3.x/css/materialdesignicons.min.css">
<link href="https://fonts.googleapis.com/css?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href=https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css>
</head>
<body>
	<div id="app">
		<v-app id="inspire"> <v-card> <v-card-title>
		<v-menu v-model="dateRangeMenu" :close-on-content-click="false"
			:nudge-right="40" transition="scale-transition" offset-y
			max-width="290px" min-width="290px">
		<template v-slot:activator="{ on }">
			<v-text-field v-model="dateRangeText" label="Date Range"
				prepend-icon="event" single-line hide-details readonly
				v-on="on" clearable  @click:clear="searchEmployees()"></v-text-field>
		</template>
		<v-date-picker v-model="picker" @input="dateRangeMenu = false"
			@change="filterDateRange" :min="minDate" :max="maxDate" no-title 
			scrollable actions range :allowed-dates="getAllowedDates"></v-date-picker>
		</v-menu> <v-spacer></v-spacer> <v-text-field v-model="search"
			append-icon="search" label="Search" single-line hide-details></v-text-field>
		</v-card-title> <v-data-table :headers="headers" :items="employees"
			:items-per-page="itemsPerPage" :search="search"
			:hide-default-footer="false" class="elevation-1">

		<template v-slot:items="props">
			<td class="text-xs-left">{{ props.item.employeeId }}</td>
			<td class="text-xs-left">{{ props.item.firstName }}</td>
			<td class="text-xs-left">{{ props.item.lastName }}</td>
			<td class="text-xs-left">{{ props.item.jobTitle }}</td>
			<td class="text-xs-left">{{ props.item.age }}</td>
			<td class="text-xs-left">{{ props.item.startDate }}</td>
			<td class="text-xs-left">{{ props.item.endDate }}</td>
		</template>
		<template v-slot:no-results>
			<v-alert :value="true" color="error" icon="warning"> Search
			for "{{ search }}" returned no results. </v-alert>
		</template>
		<template v-slot:footer>
			<div style="margin: 0px 0px -50px 15px">
				<v-dialog v-model="dialog">
				<template v-slot:activator="{ on }">
					<div class="d-flex">
						<v-btn color="primary" dark v-on="on"> New </v-btn>
					</div>
				</template>
				<v-card> <v-card-title> <span>Add
					Employee</span> </v-card-title> <v-form ref="form" v-model="isFormValid"> <v-card-text>
				<v-row> <v-col cols="12" sm="4"> <v-text-field
					v-model="newEmployee.firstName" label="First Name"
					:rules="[v => !!v || 'First name is required']" required></v-text-field>
				</v-col> <v-col cols="12" sm="4"> <v-text-field
					v-model="newEmployee.lastName" label="Last Name"
					:rules="[v => !!v || 'Last name is required']" required></v-text-field>
				</v-col> <v-col cols="12" sm="4"> <v-text-field
					v-model="newEmployee.jobTitle" label="Job Title"
					:rules="[v => !!v || 'Job title is required']" required></v-text-field>
				</v-col> <v-col cols="12" sm="4"> <v-text-field
					v-model="newEmployee.age" label="Age" type="number" min="0"
					:rules="[v => !!v || 'Age is required']" required></v-text-field> </v-col>
				<v-col cols="12" md="4"> <v-menu v-model="startDateMenu"
					:close-on-content-click="false" :nudge-right="40"
					transition="scale-transition" offset-y max-width="290px"
					min-width="290px">
				<template v-slot:activator="{ on }">
					<v-text-field v-model="startDateDisp" label="Start Date"
						prepend-icon="event" readonly
						:rules="[v => !!v || 'Start date is required']" required
						:value="startDateDisp" clearable v-on="on"></v-text-field>
				</template>
				<v-date-picker v-model="startDateVal" no-title
					@input="startDateMenu = false"></v-date-picker> </v-menu> </v-col> <v-col cols="12"
					md="4"> <v-menu v-model="endDateMenu"
					:close-on-content-click="false" :nudge-right="40"
					transition="scale-transition" offset-y max-width="290px"
					min-width="290px">
				<template v-slot:activator="{ on }">
					<v-text-field v-model="endDateDisp" label="End Date"
						prepend-icon="event" readonly :rules="newEmployeeEndDateRules"
						required :value="endDateDisp" clearable v-on="on"></v-text-field>
				</template>
				<v-date-picker v-model="endDateVal" no-title
					@input="endDateMenu = false"
					:allowed-dates="getNewEmployeeAllowedEndDates"></v-date-picker> </v-menu> </v-col> </v-row>
				</v-card-text> </v-form> <v-card-actions> <v-spacer></v-spacer> <v-btn
					color="blue" text @click="showNewEmployeeDialog()">Cancel</v-btn> <v-btn
					color="blue" :disabled="!isFormValid" text
					@click="addEmployee(newEmployee)">Save</v-btn> </v-card-actions> </v-card> </v-dialog>
			</div>
		</template>
		</v-data-table> </v-card> </v-app>
	</div>

	<!-- include Vue.js, vuetify, and axios-->
	<script
		src="https://cdn.jsdelivr.net/npm/babel-polyfill/dist/polyfill.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.x//dist/vue.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.15.2/axios.js"></script>

	<!-- Quickly load for challenge only... would normally split into seperate files and load here -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js"></script>
	<script type="text/babel">
  new Vue({
  el: '#app',
  vuetify: new Vuetify(),
  data () {
    return {
      itemsPerPage: 10,
      search: '',
      headers: [
        {
          text: 'Employee ID',
          align: 'left',
          sortable: true,
          value: 'employeeId'
        },
        { text: 'First Name', value: 'firstName' },
        { text: 'Last Name', value: 'lastName' },
        { text: 'Job Title', value: 'jobTitle' },
        { text: 'Age', value: 'age' },
        { text: 'Start Date', value: 'startDate' },
        { text: 'End Date', value: 'endDate' }
      ],
      employees: [],
      employeesCopy: [],
      dates: [],
      days: ["sunday", "monday", "tuesday","wednesday", "thursday", "friday", "saturday"],
      dateRangeMenu: false,
      picker: "",
      minDate: "",
      maxDate: "",
      dialog: false,
      isFormValid: false,
 	  newEmployee: {
 		 firstName: '', 
 		 lastName: '', 
 		 jobTitle: '', 
 		 age: null,
  		 startDate: null, 
 		 endDate: null,   
	  },
      startDateMenu: false,
      startDateVal: null,
      endDateMenu: false,
      endDateVal: null,
    }
  },
  computed: {
      dateRangeText () {
        if(this.picker !== undefined && this.dates !== undefined)
        return this.dates.join(' ~ ')
      },

   startDateDisp() {
      this.newEmployee.startDate = this.startDateVal;
      return this.startDateVal;
    },
 
   endDateDisp() {
      this.newEmployee.endDate = this.endDateVal;
      return this.endDateVal;
    },

  newEmployeeEndDateRules() {
           return [
        v => !!v || 'End date is required'];
    },
   },
  watch: {
        pagination: {
            handler() {
                this.searchEmployees();
            },
            deep: true
        }
    },
  methods: {
      searchEmployees() {
           return new Promise((resolve, reject) => {
                let search = this.search.trim().toLowerCase();

console.log("search called");

       axios
        .get(
         "/search")

                    .then(response => {
         	            let items = response.data;
                     
                        this.employees = items;
                        this.employeesCopy = items;
					
						this.minDate = new Date(Math.min.apply(null, items.map(function(e) {return new Date(e.startDate);}))).toISOString().slice(0, 10);
						this.maxDate = new Date(Math.max.apply(null, items.map(function(e) {return new Date(e.endDate);}))).toISOString().slice(0, 10);
	
                        //force picker to open at earliest data date
                        this.picker = this.minDate;
 					    resolve();
      
                    });
            });
          
        },

      addEmployee(newEmployee) {
           return new Promise((resolve, reject) => {
      
 		let formData = new FormData();

		formData.append('firstName', newEmployee.firstName);
		formData.append('lastName', newEmployee.lastName);
		formData.append('jobTitle', newEmployee.jobTitle);
		formData.append('age', newEmployee.age);
		formData.append('email', newEmployee.firstName.toLowerCase()+"."+newEmployee.lastName.toLowerCase()+"@exlservices.com");
		formData.append('startDate', new Date(newEmployee.startDate).toISOString().substring(0,10));
		formData.append('endDate', new Date(newEmployee.endDate).toISOString().substring(0,10));

 	axios
        .post(
         "/employees", formData)

                    .then(response => {
         	            let items = response.data;

                        this.employees = items;
                        this.employeesCopy = items;
					
						this.minDate = new Date(Math.min.apply(null, items.map(function(e) {return new Date(e.startDate);}))).toISOString().slice(0, 10);
						this.maxDate = new Date(Math.max.apply(null, items.map(function(e) {return new Date(e.endDate);}))).toISOString().slice(0, 10);
		            
                        //force picker to open at earliest data date
                        this.picker = this.minDate;

						this.dialog = !this.dialog
 					    resolve();
                    });
            });
        },

      getAllowedDates (value) {
	    const date = moment(value);
        const day = date.format("dddd").toLowerCase();
        return this.days.includes(day);
      },

      getNewEmployeeAllowedEndDates (value) {
        if(this.newEmployee && this.newEmployee.startDate != null)
	   	    return value >= new Date(this.newEmployee.startDate).toISOString().substr(0, 10);
		else
   	  		return value >= new Date().toISOString().substr(0, 10);
     
      },

      filterDateRange () { 
	    this.employees = this.employeesCopy;
    
   		if (this.picker !== undefined) {
   		    this.picker.sort((d1, d2) => new Date(d2) - new Date(d1)).reverse();
			this.dates = this.picker;
        	this.employees = this.employeesCopy.filter((emp) => (emp.startDate >= this.dates[0] && emp.endDate <= this.dates[1]))
   		 }
      },

 	 showNewEmployeeDialog(employee) {
        this.newEmployee = employee||{}
        this.dialog = !this.dialog
   	 },
    },
   
    mounted() {
       this.searchEmployees();
    }
})  </script>
</body>
</html>