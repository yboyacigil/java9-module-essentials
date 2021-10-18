import com.yboyacigil.scrambler.service.ScrambleService;
import com.yboyacigil.scrambler.service.ScrambleServiceImpl;

open module scrambler.service {
    requires static lombok;
    requires org.slf4j;
    requires org.slf4j.simple;

    exports com.yboyacigil.scrambler.service;
    provides ScrambleService with ScrambleServiceImpl;
}