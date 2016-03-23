/**
 * Created by Виталий on 21.12.2015.
 */
function Log($scope,$http){

    $scope.getLogs = function () {
        $http.get('/logstore/log?page='+$scope.page+'&size='+$scope.size)
            .then(
                function (responce) {
                    $scope.logs = responce.data.logs;
                    $scope.statusGet = responce.status;
                },
                function (responce) {
                    $scope.statusGet = responce.status + ", " + angular.toJson(responce.data);
                }
            );
    };

     $scope.sendMessage = function () {

         $scope.newMessage  = {
             'dt' : $scope.dt,
             'author' : $scope.author,
             'level' : $scope.level,
             'message' : $scope.message,
         };

         $http.post('/logstore/log', angular.toJson($scope.newMessage))
             .then(
                 function (responce) {
                     $scope.statusPost = responce.status + ", " + angular.toJson(responce.data);
                 },
                 function (responce) {
                     $scope.statusPost = responce.status + ", " + angular.toJson(responce.data);
                 }
             );


     }
}

