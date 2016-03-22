/**
 * Created by Виталий on 21.12.2015.
 */
function Log($scope,$http){
    $http.get('/logstore/log').
    success(function(data) {
        $scope.logs = data;
    });


     $scope.sendMessage = function () {

         $scope.newMessage  = {
             'dt' : $scope.dt,
             'author' : $scope.author,
             'level' : $scope.level,
             'message' : $scope.message,
         };

         $http.post('/logstore/log', angular.toJson($scope.newMessage)).success(function(response){
            //alert(response);
             $http.get('/logstore/log').
             success(function(data) {
                 $scope.seats = data;
             });

        });


     }
}

