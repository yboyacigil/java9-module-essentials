import com.yboyacigil.scrambler.service.ScrambleService;

module scrambler.console {
    requires static lombok;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires scrambler.service;

    uses ScrambleService;
    exports com.yboyacigil.scrambler.console;
}