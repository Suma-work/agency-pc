//男女比例饼状图
$(function (){
	$.ajax({
        url: baseURL + "stat/getStat",
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
                var dom = document.getElementById("container-cake");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {
                    title : {
                        text: '注册用户男女比例',
                        subtext: '',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['男','女','未认证']
                    },
                    series : [
                        {
                            name: '男女比例',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%','40%'],
                            data:data.data,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	
});

//console.log(sexList[1].name+"aaaaaaaaaaaaaaaaaaaa");

/*销售统计  */
var dom = document.getElementById("container-strip");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '销售统计';

option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['成交数量(辆)','成交总额(万)']
    },
    xAxis: [
        {
            type: 'category',
            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '',
            min: 0,
            max: 1000,
            interval: 200,
            axisLabel: {
                formatter: '{value}'
            }
        }
    ],
    series: [
        {
            name:'成交数量(辆)',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 105.6, 112.2, 32.6, , 6.4, 3.3]
        },
        {
            name:'成交总额(万)',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 115.6, , 48.7, 18.8, 6.0, 2.3]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}

//地图显示
$(function (){
	$.ajax({
        url: baseURL + "stat/getShopSta",
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	console.log(data.data.max);
        	var max=data.data.max;
        	var result=data.data.data;
        	var dom = document.getElementById("container-map");
        	var myChart = echarts.init(dom);
        	var app = {};
        	option = null;
        	var data = [];

        	var geoCoordMap = {
        	};

        	function convertData(data) {
        	   var res = [];
        	   for (var i = 0; i < data.length; i++) {
        	       var geoCoord = geoCoordMap[data[i].name];
        	       if (geoCoord) {
        	           res.push({
        	               name: data[i].name,
        	               value: geoCoord.concat(data[i].value)
        	           });
        	       }
        	   }
        	   return res;
        	};

        	function randomValue() {
        	    return Math.round(Math.random()*1000);
        	}

        	option = {
        	    tooltip: {},
        	    visualMap: {
        	        min: 0,
        	        max: max,
        	        left: 'left',
        	        top: 'bottom',
        	        text: ['总数',''],
        	        seriesIndex: [1],
        	        inRange: {
        	            color: ['#e0ffff', '#006edd']
        	        },
        	        calculable : true
        	    },
        	    geo: {
        	        map: 'china',
        	        roam: true,
        	        label: {
        	            normal: {
        	                show: true,
        	                textStyle: {
        	                    color: 'rgba(0,0,0,0.4)'
        	                }
        	            }
        	        },
        	        itemStyle: {
        	            normal:{
        	                borderColor: 'rgba(0, 0, 0, 0.2)'
        	            },
        	            emphasis:{
        	                areaColor: null,
        	                shadowOffsetX: 0,
        	                shadowOffsetY: 0,
        	                shadowBlur: 20,
        	                borderWidth: 0,
        	                shadowColor: 'rgba(0, 0, 0, 0.5)'
        	            }
        	        }
        	    },
        	    series : [
        	       {
        	           type: 'scatter',
        	           coordinateSystem: 'geo',
        	           data: convertData(data),
        	           symbolSize: 20,
        	           symbol: 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z',
        	           symbolRotate: 35,
        	           label: {
        	               normal: {
        	                   formatter: '{b}',
        	                   position: 'right',
        	                   show: false
        	               },
        	               emphasis: {
        	                   show: true
        	               }
        	           },
        	           itemStyle: {
        	               normal: {
        	                    color: '#F06C00'
        	               }
        	           }
        	        },
        	        {
        	            name: '入驻商家数',
        	            type: 'map',
        	            geoIndex: 0,
        	            // tooltip: {show: false},
        	            data:result
        	        }
        	    ]
        	};;
        	if (option && typeof option === "object") {
        	    myChart.setOption(option, true);
        	}
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	
});
