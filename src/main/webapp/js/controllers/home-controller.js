(function () {
    'use strict';

    angular.module('spring-boot-poc')
        .controller('HomeController', ['$scope', function($scope) {
            $scope.msg = 'Hola!';
        }]);

}());