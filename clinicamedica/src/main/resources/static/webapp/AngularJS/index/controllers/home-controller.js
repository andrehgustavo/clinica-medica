app.controller('home-controller', ['$scope', 'doctorFactory', 'specialtyFactory', function ($scope, doctorFactory, specialtyFactory) {
    $scope.filterSpecialty;
    $scope.newDoctor = {
        edit: false,
        id: "",
        name: "",
        birthday: "",
        active: true,
        specialties: "",
        tempSpecialties: "",
        tempSpecialtiesId: ""
    };
    $scope.newSpecialty = {
        edit: false,
        id: "",
        name: "",
        description: "",
        active: true
    };
    $scope.data = {
        specialties: [],
        doctors: []
    }

    specialtyFactory.listAll().then(function (value) {
        $scope.data.specialties = value;
    });
    doctorFactory.listAll().then(function (value) {
        $scope.data.doctors = value;
        let tempSpecialties = {};
        $scope.data.doctors.forEach(element => {
            element.type = "doctor";
            element.edit = true;
            var dateStr = element.birthday; //returned from mysql timestamp/datetime field
            var a = dateStr.split(" ");
            var d = a[0].split("-");
            var formatedDate = new Date(d[0], (d[1] - 1), d[2]);
            element.birthday = formatedDate;
            tempSpecialties = $scope.getTempSpecialties(element);
            element.tempSpecialties = tempSpecialties.arrayname;
            element.tempSpecialtiesId = tempSpecialties.arrayspecialties;
            tempSpecialties = {};
        });
    });

    $scope.cleanFilter = () => {
        $scope.filterSpecialty=null;
    }

    //dischard changes
    $scope.trashEdit = function (form) {
        if (form === "doctorForm") {
            $scope.newDoctor.edit = true;
            doctorFactory.findById($scope.newDoctor.id)
                .then(function (response) {
                    $scope.newDoctor.id = response.id;
                    $scope.newDoctor.name = response.name;
                    $scope.newDoctor.birthday = $scope.formateDateFromDb(response.birthday);
                    $scope.newDoctor.active = response.active;
                    $scope.newDoctor.specialties = response.specialties;
                    let tempSpecialties = {};
                    tempSpecialties = tempSpecialties = $scope.getTempSpecialties(response);
                    $scope.newDoctor.tempSpecialties = tempSpecialties.arrayname;
                    $scope.newDoctor.tempSpecialtiesId = tempSpecialties.arrayspecialties;
                });
        } else {
            $scope.newSpecialty.edit = true;
            doctorFactory.findById($scope.newSpecialty.id)
                .then(function (response) {
                    $scope.newSpecialty.id = response.id;
                    $scope.newSpecialty.name = response.name;
                    $scope.newSpecialty.description = response.description;
                    $scope.newSpecialty.active = response.active;
                });
        }
        $scope.reset(form);
    };
    $scope.reset = function (form) {
        if (form) {
            form.$setPristine();
            form.$setUntouched();
        }
        if(form==="doctorForm"){
            $scope.newDoctor = angular.copy($scope.newDoctor);
        }else{
            $scope.newSpecialty = angular.copy($scope.newSpecialty);
        }
        
    };

    $scope.sendDoctor = tempdoctor => {
        if (tempdoctor != null) {
            $scope.newDoctor.edit = true;
            $scope.newDoctor = tempdoctor;
        } else {
            $scope.newDoctor.edit = false;
            $scope.newDoctor.id = "";
            $scope.newDoctor.name = "";
            $scope.newDoctor.birthday = "";
            $scope.newDoctor.active = true;
            $scope.newDoctor.specialties = "";
            $scope.newDoctor.tempSpecialties = "";
            $scope.newDoctor.tempSpecialtiesId = "";
        }
    }

    $scope.save = (object) => {
        if(object.type !== 'undefined' && object.type === "doctor"){
            $scope.newDoctor.specialties = $scope.retrieveSpecialtiesFromId($scope.newDoctor.tempSpecialtiesId);
            doctorFactory.add($scope.newDoctor);
        }else{
            alert("especialidade")
            specialtyFactory.add($scope.newSpecialty);
        }
        
        location.reload(true);
    }
    $scope.saveEdit = (object) => {
        if(object.type !== 'undefined' && object.type === "doctor"){
            $scope.newDoctor.specialties = $scope.retrieveSpecialtiesFromId($scope.newDoctor.tempSpecialtiesId);
            let tempSpecialties = $scope.getTempSpecialties($scope.newDoctor);
            $scope.newDoctor.tempSpecialties = tempSpecialties.arrayname;
            $scope.newDoctor.tempSpecialtiesId = tempSpecialties.arrayspecialties;
            doctorFactory.update($scope.newDoctor);
        }else{
            specialtyFactory.update($scope.newSpecialty);
        }
    };

    $scope.retrieveSpecialtiesFromId = idList => {
        specialtiesList = [];
        for (const id of idList) {
            for (const specialty of $scope.data.specialties) {
                if (id == specialty.id) {
                    specialtiesList.push(specialty);
                }
            }
        }
        return specialtiesList;
    }

    $scope.formateDateFromDb = datedB => {
        var dateStr = datedB; //returned from mysql timestamp/datetime field
        var a = dateStr.split(" ");
        var d = a[0].split("-");
        return new Date(d[0], (d[1] - 1), d[2]);
    }

    $scope.getTempSpecialties = doctor => {
        let tempSpecialties = {
            arrayname: [],
            arrayspecialties: []
        }
        doctor.specialties.forEach(spec => {
            tempSpecialties.arrayname.push(spec.name);
            tempSpecialties.arrayspecialties.push(spec.id);
        })
        return tempSpecialties;
    }

    $scope.delete = object => {
        if (object.type == "doctor") {
            let box = confirm("Você tem certeza que deseja excluir o médico " + object.name + " ?");
            if (box === true) {
                doctorFactory.delet(object.id)
                    .then(function (value) {
                        location.reload(true);
                    });
            }
        } else {
            let box = confirm("Você tem certeza que deseja excluir a especialidade " + object.name + " ?");
            if (box === true) {
                specialtyFactory.delet(object.id)
                    .then(function (value) {
                        location.reload(true);
                    });
            }
        }
    };


    $scope.sendSpecialty = tempspecialty => {
        if (tempspecialty !== null) {
            $scope.newSpecialty = tempspecialty;
            $scope.newSpecialty.edit = true;
        } else {
            $scope.newSpecialty.edit = false;
            $scope.newSpecialty.id = "";
            $scope.newSpecialty.name = "";
            $scope.newSpecialty.birthday = "";
            $scope.newSpecialty.active = true;
        }
    }

    $scope.orderByField = function(field){
        $scope.orderCriteria = field;
        $scope.orderDirection = !$scope.orderDirection;
    };

}]);
