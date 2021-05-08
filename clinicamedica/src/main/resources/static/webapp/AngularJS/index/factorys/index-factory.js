app.factory('indexFactory', function ($http) {
    return {
        getVersion: getVersion,
    };

    function complete(response) {
        return response.data;
    }

    function failed(response) {
        return response.statusText;
    }

    function getVersion() {
        return $http.get('api/version').then(complete).catch(failed);;
    }
});