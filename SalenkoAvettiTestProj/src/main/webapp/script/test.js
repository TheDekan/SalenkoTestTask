var app = angular.module('tests', ['ngResource', 'ngGrid', 'ui.bootstrap']);

app.controller('testsListController', function ($scope, $rootScope, testService) {
    
    $scope.sortInfo = {fields: ['id'], directions: ['asc']};
    $scope.tests = {currentPage: 1};

    $scope.gridOptions = {
        data: 'tests.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,

        columnDefs: [
            { field: 'id', displayName: 'Id' },
            { field: 'parametertext', displayName: 'Parametertext' },
            { field: 'parameterint', displayName: 'Parameterint' },   
            { field: '', width: 30, cellTemplate: '<span class="glyphicon glyphicon-remove remove" ng-click="deleteRow(row)"></span>' }          
        ],

        multiSelect: false,
        selectedItems: [],
        
        afterSelectionChange: function (rowItem) {
            if (rowItem.selected) {
                $rootScope.$broadcast('testSelected', $scope.gridOptions.selectedItems[0].id);
            }
        }
    };

        $scope.refreshGrid = function () {
        var listTestsArgs = {
            page: $scope.tests.currentPage,
            sortFields: $scope.sortInfo.fields[0],
            sortDirections: $scope.sortInfo.directions[0]
        };

        testService.get(listTestsArgs, function (data) {
            $scope.tests = data;
        })
    };

        $scope.deleteRow = function (row) {
        $rootScope.$broadcast('deleteTest', row.entity.id);
    };

        $scope.$watch('sortInfo', function () {
        $scope.tests = {currentPage: 1};
        $scope.refreshGrid();
    }, true);

        $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

        $scope.$on('refreshGrid', function () {
        $scope.refreshGrid();
    });

        $scope.$on('clear', function () {
        $scope.gridOptions.selectAll(false);
    });
});

app.controller('testsFormController', function ($scope, $rootScope, testService) {
    
    	$scope.clearForm = function () {
        $scope.test = null;
        $scope.testForm.$setPristine();
        $rootScope.$broadcast('clear');
    };

        $scope.updateTest = function () {
        testService.save($scope.test).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('testSaved');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    };

    	$scope.$on('testSelected', function (event, id) {
        $scope.test = testService.get({id: id});
    });

        $scope.$on('deleteTest', function (event, id) {
        testService.delete({id: id}).$promise.then(
            function () {
                $rootScope.$broadcast('refreshGrid');
                $rootScope.$broadcast('testDeleted');
                $scope.clearForm();
            },
            function () {
                $rootScope.$broadcast('error');
            });
    });
});

app.controller('alertMessagesController', function ($scope) {
    	$scope.$on('testSaved', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record saved successfully!' }
        ];
    });

        $scope.$on('testDeleted', function () {
        $scope.alerts = [
            { type: 'success', msg: 'Record deleted successfully!' }
        ];
    });

        $scope.$on('error', function () {
        $scope.alerts = [
            { type: 'danger', msg: 'There was a problem in the server!' }
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
});

app.factory('testService', function ($resource) {
    return $resource('resources/tests/:id');
});
