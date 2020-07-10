//Conductivity chart options
var optionsCond = {
  chart: {
    type: 'area',
    height: 350 ,
    width: '100%',
  },
  series: [{
    name: 'Init Cond',
    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
  }],
  xaxis: {
    categories: [100,200,300,400,500,600,700,800,900,1000, 1100, 1200, 1300, 1400, 1500,
      1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000,
      3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200, 4300, 4400, 4500,
      4600, 4700, 4800, 4900, 5000, 5100, 5200, 5300, 5400, 5500, 5600, 5700, 5800, 5900, 6000],
    title: {
          text: 'Frequency [MHz]',
          style: {
            fontSize:  '15px',
            fontWeight:  'normal',
            fontFamily:  undefined,
            color:  '#263238'
          }
    }
  },
  yaxis: {
    title: {
          text: 'Conductivity [S/m]',
          style: {
            fontSize:  '15px',
            fontWeight:  'normal',
            fontFamily:  undefined,
            color:  '#263238'
          }
    }
 },
  colors:['#f5a300', '#ae00ff', '#99de0d', '#ff33cc', '#00b3b3'],
  stroke: {
    curve: 'smooth',
    width: 2
  },
  fill: {
    type: 'gradient',
    gradient: {
      shadeIntensity: 1
    }
  },
  dataLabels: {
          enabled: false,
  },
};

//Chart Init
var condChart = new ApexCharts(document.querySelector("#condChart"), optionsCond);

//Chart Render
condChart.render();

//Chart Options
var optionsPerm = {
  chart: {
    type: 'area',
    height: 350,
    width: '100%'
  },
  series: [{
    name: 'Init Perm',
    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
  }],
  xaxis: {
    categories: [100,200,300,400,500,600,700,800,900,1000, 1100, 1200, 1300, 1400, 1500, 1600,
      1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600, 2700, 2800, 2900, 3000,
      3100, 3200, 3300, 3400, 3500, 3600, 3700, 3800, 3900, 4000, 4100, 4200, 4300, 4400, 4500,
      4600, 4700, 4800, 4900, 5000, 5100, 5200, 5300, 5400, 5500, 5600, 5700, 5800, 5900, 6000],
    title: {
      text: 'Frequency [MHz]',
      style: {
        fontSize:  '15px',
        fontWeight:  'normal',
        fontFamily:  undefined,
        color:  '#263238'
      }
    }
  },
  yaxis: {
    title: {
          text: 'Relative permittivity',
          style: {
            fontSize:  '15px',
            fontWeight:  'normal',
            fontFamily:  undefined,
            color:  '#263238'
          }
    }
  },
    colors:['#f5a300', '#ae00ff', '#99de0d', '#ff33cc', '#00b3b3'],
    stroke: {
      curve: 'smooth',
      width: 2
    },
    fill: {
      type: 'gradient',
      gradient: {
        shadeIntensity: 1
      }
    },
    dataLabels: {
            enabled: false,
    },
};

//Chart Init
var permChart = new ApexCharts(document.querySelector("#permChart"), optionsPerm);

//Chart Render
permChart.render();

function appendseriescond(result) {
  condChart.appendSeries({
    name: 'Cond ' + countChanges,
    data: result
  })
}

function appendseriesperm(result) {
  permChart.appendSeries({
    name: 'Perm ' + countChanges,
    data: result
  })
}

var countChanges;

    $(function(){
  $('#debyeForm').on('submit', function(event){
    event.preventDefault();
    $.getJSON('http://localhost:8080/calculateMatrix/?conduct_sg_0=' + document.getElementById('conduct_sg_0').value +
        '&e_inf=' + document.getElementById('e_inf').value + '&e_stat=' +
        document.getElementById('e_stat').value + '&tau=' +
        document.getElementById('tau').value, function(result){
      if(countChanges === 1) {
        permChart.updateSeries([{
          data: result['perm']}], true);
        condChart.updateSeries([{
          data: result['cond']}], true);
      }
      else {
        appendseriescond(result['cond']);
        appendseriesperm(result['perm']);
      }
    });
    countChanges = countChanges + 1;
    console.log("Form Submission stopped.");
  });
});


$(function(){
  $('#getTissueId').on('submit', function(event){
    event.preventDefault();
    $.getJSON('http://localhost:8080/getParams/?id=' + (document.getElementById('tissues').selectedIndex +1) , function(result){
      document.getElementById('conduct_sg_0').value = result['conduct_sg_0'];
      document.getElementById('e_stat').value = result['e_stat'];
      document.getElementById('e_inf').value = result['e_inf'];
      document.getElementById('tau').value = result['tau'];
    });
    countChanges = 0;
    console.log("Form Submission stopped.");

  });
});