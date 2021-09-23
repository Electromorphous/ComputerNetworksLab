set ns [new Simulator]
set tf [open trace.tr w]
$ns trace-all $tf
set nf [open nam.nam w]
$ns namtrace-all-wireless $nf 600 600

set topo [new Topography]
$topo load_flatgrid 600 600

$ns node-config \
-adhocRouting DSDV \
-llType LL \
-macType Mac/802_11 \
-ifqType Queue/DropTail \
-ifqLen 50 \
-phyType Phy/WirelessPhy \
-channelType Channel/WirelessChannel \
-propType Propagation/TwoRayGround \
-antType Antenna/OmniAntenna \
-topoInstance $topo \
-agentTrace ON \
-routerTrace ON

create-god 3

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$n0 label "tcp0"
$n1 label "sink0 / tcp1"
$n2 label "sink1"

$n0 set X_ 50
$n0 set Y_ 50
$n0 set Z_ 0

$n1 set X_ 100
$n1 set Y_ 100
$n1 set Z_ 0

$n2 set X_ 500
$n2 set Y_ 500
$n2 set Z_ 0

$ns at 0.1 "$n0 setdest 50 50 0"
$ns at 0.1 "$n1 setdest 100 100 0"
$ns at 0.1 "$n2 setdest 500 500 0"

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n1 $sink0
$ns connect $tcp0 $sink0

set tcp1 [new Agent/TCP]
$ns attach-agent $n1 $tcp1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n2 $sink1
$ns connect $tcp1 $sink1

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    exec nam nam.nam
    exit 0
}

$ns at 5 "$ftp0 start"
$ns at 5 "$ftp1 start"
$ns at 50 "$n1 setdest 450 450 30"
$ns at 100 "$n1 setdest 100 100 30"
$ns at 130 "finish"
$ns run
