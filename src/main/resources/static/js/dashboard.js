$(function () {
    const contextPath = $('meta[name="context"]').attr('content') ?? '';
    //Load report on page load
    loadReport();

    //Validate end date
    $.validator.addMethod("validateEndDate", function () {
        const startDate = $("#startDate").val();
        const endDate = $("#endDate").val();
        if (((Date.parse(endDate) - Date.parse(startDate)) >= 0) && ((Date.parse(endDate) - Date.now()) <= 0)) {
            return true;
        }
        return false;
    }, "Start date date must be before end date and today's date");

    $("#selectDate").validate({
        rules: {
            startDate: {
                required: true,
            },
            endDate: {
                required: true,
                validateEndDate: true,
            },
        },
        messages: {
            startDate: {
                required: " Please input open date",
            },
            endDate: {
                required: " Please input end date",
            }
        },
        errorElement: "error"
    });

    const formDashboard = $("#selectDate");
    formDashboard.on('submit', function (e){
        e.preventDefault();
        if (formDashboard.valid()){
            loadReport();
        }
    });

    //Load all data
    function loadReport() {
        let startDate = $('#startDate').val();
        let endDate = $('#endDate').val();
        let option = "default";
        if (startDate.length > 0 && endDate.length > 0) {
            option = "filter";
        }
        loadReportFresherClass(option);
        loadReportFresherTrainee(option);
        loadReportInternClass(option);
        loadReportInternTrainee(option);
    }

    //Load report follow fresher class
    function loadReportFresherClass(option) {
        let selector = $('#chartFresherClass');
        let path = "";
        let data;
        if (option == "filter") {
            path += "/report-fresher-class";
            data = $('#selectDate').serialize();
        } else {
            path += "/report-fresher-class-default";
            data = null;
        }
        callApi(selector, path, data);
    }

    //Load report follow fresher trainee
    function loadReportFresherTrainee(option) {
        let selector = $('#chartFresherTrainee');
        let path = "";
        let data;
        if (option == "filter") {
            path += "/report-fresher-trainee";
            data = $('#selectDate').serialize();
        } else {
            path += "/report-fresher-trainee-default";
            data = null;
        }
        callApi(selector, path, data);
    }

    //Load report follow intern class
    function loadReportInternClass(option) {
        let selector = $('#chartInternClass');
        let path = "";
        let data;
        if (option == "filter") {
            path += "/report-internship-class";
            data = $('#selectDate').serialize();
        } else {
            path += "/report-internship-class-default";
            data = null;
        }
        callApi(selector, path, data);
    }

    //Load report follow intern trainee
    function loadReportInternTrainee(option) {
        let selector = $('#chartInternTrainee');
        let path = "";
        let data;
        if (option == "filter") {
            path += "/report-internship-trainee";
            data = $('#selectDate').serialize();
        } else {
            path += "/report-internship-trainee-default";
            data = null;
        }
        callApi(selector, path, data);
    }

    function callApi(selector, path, data) {
        $.ajax({
            url: contextPath + path,
            type: 'GET',
            dataType: 'json',
            data: data,
            success: function (res) {
                renderChart(selector, res);
            },
            error: function (error) {
                displayError(selector, error);
            }
        });
    }

    function renderChart(selector, res) {
        if (res["countWaiting"] == 0 && res["countRunning"] == 0 && res["countRelease"] == 0) {
            selector.html(`<p class="text-danger fs-4">No Data!</p>`);
        } else {
            let optionsChart = {
                series: [res["countWaiting"], res["countRunning"], res["countRelease"]],

                labels: ['Waiting', 'Release', 'Running'],
                colors: ['#EBA900', '#EB543E', '#36A2F5'],
                chart: {
                    type: 'donut',
                    width: '100%',
                    height: '500px'
                },
                legend: {
                    position: 'top'
                },
                plotOptions: {
                    pie: {
                        donut: {
                            size: '30%'
                        }
                    }
                }
            }

            let chart = new ApexCharts(selector.get(0), optionsChart);
            chart.render();
            chart.update();
        }
    }

    //Display message if api call fail
    function displayError(selector, error) {
        let msg = JSON.parse(error.responseText);
        selector.html(`<p class="text-danger">Status Error: ${msg.status}</p>
                            <p class="text-danger">An error occurred while loading the chart</p>`);
    }
});