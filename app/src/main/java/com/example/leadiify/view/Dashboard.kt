package com.example.leadiify.view


import android.graphics.Color
import android.os.Bundle
import com.anychart.enums.Anchor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke
import com.example.leadiify.R
import com.example.leadiify.adapter.HomeStatsAdapter
import com.example.leadiify.databinding.FragmentDashboardBinding
import com.example.leadiify.model.home_stats_model
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class Dashboard : Fragment() {
    var barChart: BarChart? = null

    var barDataSet1: BarDataSet? = null
    var barDataSet2: BarDataSet? = null

    var barEntries: ArrayList<BarEntry>? = null

    var days = arrayOf("Sunday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentDashboardBinding.inflate(inflater, container, false)
        var statsData=ArrayList<home_stats_model>()
        extracted(statsData)
        var adapter=HomeStatsAdapter(statsData)
        var layoutManager= StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=layoutManager
         barChart = binding.groupedBarChart

        barCharts()
//        lineChart(binding)
        funnelChart(binding.funnelChart)
        return binding.root
    }

    private fun lineChart(lChart :AnyChartView) {
        val cartesian = AnyChart.line()

        cartesian.animation(true)
        cartesian.padding(10.0, 20.0, 5.0, 20.0)
        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true) // TODO ystroke
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

        cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.")
        cartesian.yAxis(0).title("Number of Bottles Sold (thousands)")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData: MutableList<DataEntry> = ArrayList()
        seriesData.add(CustomDataEntry("1986", 3.6, 2.3, 2.8))
        seriesData.add(CustomDataEntry("1987", 7.1, 4.0, 4.1))
        seriesData.add(CustomDataEntry("1988", 8.5, 6.2, 5.1))
        seriesData.add(CustomDataEntry("1989", 9.2, 11.8, 6.5))
        seriesData.add(CustomDataEntry("1990", 10.1, 13.0, 12.5))
        seriesData.add(CustomDataEntry("1991", 11.6, 13.9, 18.0))
        seriesData.add(CustomDataEntry("1992", 16.4, 18.0, 21.0))
        seriesData.add(CustomDataEntry("1993", 18.0, 23.3, 20.3))
        seriesData.add(CustomDataEntry("1994", 13.2, 24.7, 19.2))
        seriesData.add(CustomDataEntry("1995", 12.0, 18.0, 14.4))
        seriesData.add(CustomDataEntry("1996", 3.2, 15.1, 9.2))
        seriesData.add(CustomDataEntry("1997", 4.1, 11.3, 5.9))
        seriesData.add(CustomDataEntry("1998", 6.3, 14.2, 5.2))
        seriesData.add(CustomDataEntry("1999", 9.4, 13.7, 4.7))
        seriesData.add(CustomDataEntry("2000", 11.5, 9.9, 4.2))
        seriesData.add(CustomDataEntry("2001", 13.5, 12.1, 1.2))
        seriesData.add(CustomDataEntry("2002", 14.8, 13.5, 5.4))
        seriesData.add(CustomDataEntry("2003", 16.6, 15.1, 6.3))
        seriesData.add(CustomDataEntry("2004", 18.1, 17.9, 8.9))
        seriesData.add(CustomDataEntry("2005", 17.0, 18.9, 10.1))
        seriesData.add(CustomDataEntry("2006", 16.6, 20.3, 11.5))
        seriesData.add(CustomDataEntry("2007", 14.1, 20.7, 12.2))
        seriesData.add(CustomDataEntry("2008", 15.7, 21.6, 10))
        seriesData.add(CustomDataEntry("2009", 12.0, 22.5, 8.9))

        val set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

        val series1 = cartesian.line(series1Mapping)
        series1.name("Brandy")
        series1.hovered().markers().enabled(true)
        series1.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series1.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        val series2 = cartesian.line(series2Mapping)
        series2.name("Whiskey")
        series2.hovered().markers().enabled(true)
        series2.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series2.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        val series3 = cartesian.line(series3Mapping)
        series3.name("Tequila")
        series3.hovered().markers().enabled(true)
        series3.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        series3.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13.0)
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)
        lChart.setChart(cartesian)
    }

    private fun barCharts() {
        // creating a new bar data set.

        // creating a new bar data set.
        barDataSet1 = BarDataSet(getBarEntriesOne(), "First Set")
        barDataSet1!!.setColor(Color.parseColor("#0095FF"))
        barDataSet2 = BarDataSet(getBarEntriesTwo(), "Second Set")
        barDataSet2!!.setColor(
            Color.parseColor(
                "#00E096"
            )
        )

        // below line is to add bar data set to our bar data.

        // below line is to add bar data set to our bar data.
        val data = BarData(barDataSet1, barDataSet2)

        // after adding data to our bar data we
        // are setting that data to our bar chart.

        // after adding data to our bar data we
        // are setting that data to our bar chart.
        barChart!!.setData(data)

        // below line is to remove description
        // label of our bar chart.

        // below line is to remove description
        // label of our bar chart.
        barChart!!.getDescription().setEnabled(false)

        // below line is to get x axis
        // of our bar chart.

        // below line is to get x axis
        // of our bar chart.
        val xAxis: XAxis = barChart!!.getXAxis()

        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.

        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.
        xAxis.valueFormatter = IndexAxisValueFormatter(days)

        // below line is to set center axis
        // labels to our bar chart.

        // below line is to set center axis
        // labels to our bar chart.
        xAxis.setCenterAxisLabels(true)

        // below line is to set position
        // to our x-axis to bottom.

        // below line is to set position
        // to our x-axis to bottom.
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // below line is to set granularity
        // to our x axis labels.

        // below line is to set granularity
        // to our x axis labels.
        xAxis.granularity = 1f

        // below line is to enable
        // granularity to our x axis.

        // below line is to enable
        // granularity to our x axis.
        xAxis.isGranularityEnabled = true

        // below line is to make our
        // bar chart as draggable.

        // below line is to make our
        // bar chart as draggable.
        barChart!!.setDragEnabled(true)

        // below line is to make visible
        // range for our bar chart.

        // below line is to make visible
        // range for our bar chart.
        barChart!!.setVisibleXRangeMaximum(3F)

        // below line is to add bar
        // space to our chart.

        // below line is to add bar
        // space to our chart.
        val barSpace = 0.1f

        // below line is use to add group
        // spacing to our bar chart.

        // below line is use to add group
        // spacing to our bar chart.
        val groupSpace = 0.5f

        // we are setting width of
        // bar in below line.

        // we are setting width of
        // bar in below line.
        data.barWidth = 0.15f

        // below line is to set minimum
        // axis to our chart.

        // below line is to set minimum
        // axis to our chart.
        barChart!!.getXAxis().setAxisMinimum(0F)

        // below line is to
        // animate our chart.

        // below line is to
        // animate our chart.
        barChart!!.animate()

        // below line is to group bars
        // and add spacing to it.

        // below line is to group bars
        // and add spacing to it.
        barChart!!.groupBars(0F, groupSpace, barSpace)

        // below line is to invalidate
        // our bar chart.

        // below line is to invalidate
        // our bar chart.
        barChart!!.invalidate()
    }

    private fun extracted(statsData: ArrayList<home_stats_model>) {
        statsData.add(
            home_stats_model(
                "Active", R.drawable.group_1000004869, "10",
                "#4478F2"
            )
        )
        statsData.add(
            home_stats_model(
                "Paused", R.drawable.group_1000004872, "9",
                "#FEC400"
            )
        )
        statsData.add(
            home_stats_model(
                "Stopped", R.drawable.group_1000004875, "12",
                "#FE1E00"
            )
        )
        statsData.add(
            home_stats_model(
                "Completed", R.drawable.group_1000004876, "16",
                "#29CC4D"
            )
        )
        statsData.add(
            home_stats_model(
                "Flagged", R.drawable.group_1000004878, "28",
                "#FE4C00"
            )
        )
        statsData.add(
            home_stats_model(
                "Total", R.drawable.group_1000004889, "75",
                "#FE00A8"
            )
        )
    }

    private fun funnelChart(anyChartView: AnyChartView) {
        val funnel = AnyChart.funnel()
        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Awareness", 15))
        data.add(ValueDataEntry("Interest", 12))
        data.add(ValueDataEntry("Desire",10 ))
        data.add(ValueDataEntry("Action", 7))
        funnel.data(data)
        funnel.margin(arrayOf("10", "20%", "10", "20%"))
        funnel.baseWidth("70%")
            .neckWidth("17%")
        funnel.labels()
            .position("outsideleft")
            .format("{%X} - {%Value}")

        funnel.animation(true)
        anyChartView.setChart(funnel)
    }
    private fun getBarEntriesOne(): ArrayList<BarEntry>? {

        // creating a new array list
        barEntries = ArrayList<BarEntry>()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries!!.add(BarEntry(1f, 4f))
        barEntries!!.add(BarEntry(2f, 6f))
        barEntries!!.add(BarEntry(3f, 8f))
        barEntries!!.add(BarEntry(4f, 2f))
        barEntries!!.add(BarEntry(5f, 4f))
        barEntries!!.add(BarEntry(6f, 1f))
        return barEntries
    }

    // array list for second set.
    private fun getBarEntriesTwo(): ArrayList<BarEntry>? {

        // creating a new array list
        barEntries = ArrayList<BarEntry>()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries!!.add(BarEntry(1f, 4f))
        barEntries!!.add(BarEntry(2f, 6f))
        barEntries!!.add(BarEntry(3f, 8f))
        barEntries!!.add(BarEntry(4f, 2f))
        barEntries!!.add(BarEntry(5f, 4f))
        barEntries!!.add(BarEntry(6f, 1f))
        return barEntries
    }
    private class CustomDataEntry internal constructor(
        x: String?,
        value: Number?,
        value2: Number?,
        value3: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }


}