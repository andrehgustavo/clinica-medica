app.controller('home-controller', ['$scope', 'doctorFactory', function ($scope, doctorFactory) {
    let vm = this;
    vm.teste = "";
    vm.name="André"
    $scope.newDoctor;
    $scope.teste;
    $scope.doctors=[];

    doctorFactory.listAll().then(function (value) {
        $scope.doctors = value;
        vm.teste = value;
        let arrayname = [];
        $scope.doctors.forEach(element => {
            var dateStr=element.birthday; //returned from mysql timestamp/datetime field
            var a=dateStr.split(" ");
            var d=a[0].split("-");
            var formatedDate = new Date(d[0],(d[1]-1),d[2]);
            element.birthday = formatedDate;
            element.specialties.forEach(spec => {
                arrayname.push(spec.name);
            })
            element.tempSpecialties = arrayname;
            arrayname = [];
        });
        console.log('andre ');
        console.log($scope.doctors);        
    });

    $scope.newForm = () => {
        location.replace('#!/doctors/newDoctor');
    }

    $scope.sendDoctor = tempdoctor => {
        console.log(tempdoctor);
        $scope.newDoctor = tempdoctor;
    }

    
}]);
