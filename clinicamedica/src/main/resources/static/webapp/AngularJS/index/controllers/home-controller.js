app.controller('home-controller', ['$scope', 'doctorFactory', 'specialtyFactory', function ($scope, doctorFactory, specialtyFactory) {

    $scope.newDoctor = {
        edit: false,
        id: "",
        name: "",
        birthday: "",
        active: "",
        specialties: "",
        tempSpecialties: "",
        tempSpecialtiesId: ""
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

    //dischard changes
    $scope.trashEdit = function (form) {
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
        $scope.reset(form);
    };
    $scope.reset = function (form) {
        if (form) {
            form.$setPristine();
            form.$setUntouched();
        }
        $scope.newDoctor = angular.copy($scope.newDoctor);
    };

    $scope.newForm = () => {
        location.replace('#!/doctors/newDoctor');
    }

    $scope.sendDoctor = tempdoctor => {
        if (tempdoctor != null) {
            $scope.newDoctor.edit = true;
            $scope.newDoctor = tempdoctor;
        } else {
            $scope.newDoctor.edit = false;
            $scope.newDoctor.id = "";
            $scope.newDoctor.name = "";
            $scope.newDoctor.birthday = "";
            $scope.newDoctor.active = "";
            $scope.newDoctor.specialties = "";
            $scope.newDoctor.tempSpecialties = "";
            $scope.newDoctor.tempSpecialtiesId = "";
        }
    }

    $scope.save = () => {
        $scope.newDoctor.specialties = $scope.retrieveSpecialtiesFromId($scope.newDoctor.tempSpecialtiesId);
        doctorFactory.add($scope.newDoctor);
        location.reload(true);
    }
    $scope.saveEdit = () => {
        $scope.newDoctor.specialties = $scope.retrieveSpecialtiesFromId($scope.newDoctor.tempSpecialtiesId);
        let tempSpecialties = $scope.getTempSpecialties($scope.newDoctor);
        $scope.newDoctor.tempSpecialties = tempSpecialties.arrayname;
        $scope.newDoctor.tempSpecialtiesId = tempSpecialties.arrayspecialties;
        doctorFactory.update($scope.newDoctor);
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

    $scope.delete = doctor => {
        let box = confirm("Você tem certeza que deseja excluir o médico " + doctor.name + " ?");
        if (box === true) {
            doctorFactory.delet(doctor.id)
                .then(function (value) {
                    location.reload(true);
                });
        }
    };

    $scope.submitForm = () => {
        alert("Alerta")
        if ($scope.doctorForm) {
            alert('our form is amazing');
        }
        //jQuery('#doctorModalEdit').modal('hide');
    }

    $scope.sendSpecialty = tempdoctor => {
        if (tempdoctor != null) {
            $scope.newSpecialty.edit = true;
            $scope.newSpecialty = tempdoctor;
        } else {
            $scope.newSpecialty.edit = false;
            $scope.newSpecialty.id = "";
            $scope.newSpecialty.name = "";
            $scope.newSpecialty.birthday = "";
            $scope.newSpecialty.active = "";
            $scope.newSpecialty.specialties = "";
            $scope.newSpecialty.tempSpecialties = "";
            $scope.newSpecialty.tempSpecialtiesId = "";
        }
    }


}]);
