$.ajax({
    /* for pie chart */
    url: "piechartdata",
    /* for line chart */
    //	url: "linechartdata",
    /* for multiple line chart */
    //	url: "multiplelinechartdata",
    success: function(result) {
        /* line chart single starts here */
        //		var category = JSON.parse(result).categories;
        //		var series = JSON.parse(result).series;
        //		drawLineChart(category, series);
        /* line chart single ends here */

        /* line chart multiple series starts here */
        //		var formatteddata = [];
        //		for(var key in result){
        //			var singleObject = {
        //					name: '',
        //					data: []
        //				}
        //			singleObject.name = key.toUpperCase();
        //			for(var i = 0; i < result[key].length; i++){
        //				singleObject.data.push(parseInt(result[key][i].subscribers));
        //			}
        //			formatteddata.push(singleObject);
        //		}
        //		
        //		drawMultipleLineChart(formatteddata);
        /* line chart multiple series ends here */

        /* pie chart starts here */
        var series = [];
        var data = [];

        for (var i = 0; i < result.length; i++) {
            var object = {};
            object.name = result[i].estado.toUpperCase();
            object.y = result[i].estado;
            data.push(object);
        }
        var seriesObject = {
            name: 'Employees',
            colorByPoint: true,
            data: data
        };
        series.push(seriesObject);
        drawPieChart(series);

        /* pie chart ends here */
    }
});

/* for pie chart */
function drawPieChart(series) {
    Highcharts.chart('container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Browser market shares in January, 2018'
        },
        tooltip: {
            formatter: function() {
                return '<strong>' + this.key + ': </strong>' + this.y;
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.y}'
                }
            }
        },
        series: series
    });
}