app.factory('specialtyFactory', function ($http) {
    return {
        listAll: listAll,
        findById: findById,
        add: add,
        update: update,
        delet: delet
        
    };

    function complete(response) {
        return response.data;
    }

    function failed(response) {
        return response.statusText;
    }

    function listAll() {
        return $http.get('http://localhost:8084/api/specialties').then(complete).catch(failed);
    }
    
    function findById(id) {
        return $http.get('http://localhost:8084/api/specialties/' + id).then(complete).catch(failed);
    }

    function add(doctor) {
        return $http.post('http://localhost:8084/api/specialties', doctor).then(complete).catch(failed);
    }
    function update(doctor) {
        return $http.put('http://localhost:8084/api/specialties', doctor).then(complete).catch(failed);
    }

    function delet(id) {
        return $http.delete('http://localhost:8084/api/specialties/' + id).then(complete).catch(failed);
    }
});