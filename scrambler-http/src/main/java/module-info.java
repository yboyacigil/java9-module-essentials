import com.yboyacigil.scrambler.service.ScrambleService;

module scrambler.http {
    requires static lombok;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires jdk.httpserver;

    requires scrambler.service;
    uses ScrambleService;
}