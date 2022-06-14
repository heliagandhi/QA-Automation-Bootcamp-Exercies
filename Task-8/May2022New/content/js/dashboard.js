/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.4807692307692308, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.5, 500, 1500, "/product-desc"], "isController": false}, {"data": [0.55, 500, 1500, "/histori"], "isController": false}, {"data": [0.5, 500, 1500, "/list"], "isController": false}, {"data": [0.5, 500, 1500, "/merchant"], "isController": false}, {"data": [0.55, 500, 1500, "/search"], "isController": false}, {"data": [0.5, 500, 1500, "/recomendation"], "isController": false}, {"data": [0.5, 500, 1500, "/personalized-ads"], "isController": false}, {"data": [0.5, 500, 1500, "/promo"], "isController": false}, {"data": [0.5, 500, 1500, "/review"], "isController": false}, {"data": [0.6, 500, 1500, "/order"], "isController": false}, {"data": [0.5, 500, 1500, "/top-ads"], "isController": false}, {"data": [0.0, 500, 1500, "/category"], "isController": false}, {"data": [0.55, 500, 1500, "/checkout"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 130, 0, 0.0, 1047.4076923076925, 432, 3684, 914.0, 1115.0, 3238.649999999999, 3674.08, 8.601296810903799, 156.0074423961228, 1.511946705041683], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["/product-desc", 10, 0, 0.0, 863.4, 600, 1008, 899.5, 1006.4, 1008.0, 1008.0, 3.1289111389236544, 56.75001466677096, 0.5500039111389237], "isController": false}, {"data": ["/histori", 10, 0, 0.0, 806.3, 446, 984, 823.5, 983.8, 984.0, 984.0, 6.7114093959731544, 121.72294463087249, 1.179739932885906], "isController": false}, {"data": ["/list", 10, 0, 0.0, 953.0, 748, 1063, 970.5, 1061.4, 1063.0, 1063.0, 3.1725888324873095, 57.542210302982234, 0.5576816307106599], "isController": false}, {"data": ["/merchant", 10, 0, 0.0, 880.7, 623, 1115, 874.0, 1113.6, 1115.0, 1115.0, 2.741979709350151, 49.730782578146425, 0.48198862078420623], "isController": false}, {"data": ["/search", 10, 0, 0.0, 929.1, 486, 1139, 958.5, 1136.5, 1139.0, 1139.0, 3.259452411994785, 59.121819487451106, 0.5729506192959583], "isController": false}, {"data": ["/recomendation", 10, 0, 0.0, 872.8000000000001, 584, 1115, 952.0, 1105.1000000000001, 1115.0, 1115.0, 2.9120559114735003, 52.82054227941176, 0.5118848281887012], "isController": false}, {"data": ["/personalized-ads", 10, 0, 0.0, 832.5, 594, 1056, 816.5, 1050.6, 1056.0, 1056.0, 8.818342151675484, 159.94181960978838, 1.5500992063492065], "isController": false}, {"data": ["/promo", 10, 0, 0.0, 870.2, 477, 2006, 800.5, 1897.0000000000005, 2006.0, 2006.0, 3.567606136282554, 64.70919829200857, 0.6271182661434178], "isController": false}, {"data": ["/review", 10, 0, 0.0, 858.2, 585, 1046, 924.0, 1041.2, 1046.0, 1046.0, 3.0102347983142685, 54.59930952739314, 0.52914283564118], "isController": false}, {"data": ["/order", 10, 0, 0.0, 660.8, 432, 987, 655.0, 981.0, 987.0, 987.0, 3.8580246913580245, 69.97416931905865, 0.6781684027777778], "isController": false}, {"data": ["/top-ads", 10, 0, 0.0, 951.2, 851, 1003, 954.5, 1001.7, 1003.0, 1003.0, 3.2289312237649335, 58.56631669761059, 0.5675855666774298], "isController": false}, {"data": ["/category", 10, 0, 0.0, 3328.7000000000003, 2836, 3684, 3335.5, 3680.8, 3684.0, 3684.0, 2.6888948642108095, 48.76930542484539, 0.47265730034955633], "isController": false}, {"data": ["/checkout", 10, 0, 0.0, 809.4, 452, 1045, 832.0, 1038.8, 1045.0, 1045.0, 3.238341968911917, 58.73858990446891, 0.5692397992227979], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 130, 0, null, null, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
