package org.tools4j.launcher.service

import spock.lang.Specification

/**
 * User: ben
 * Date: 2/11/17
 * Time: 6:45 AM
 */
class PartIndexTest extends Specification {
    final String csv = """h,f,e,g,a,d,l,c,n
haud0001,haud0001.tools4j.com,dev,dev-1,webserver,~/webserver,~/webserver/logs,Australia,DevWebAustralia
haud0001,haud0001.tools4j.com,dev,dev-1,mailserver,~/mailserver,~/mailserver/logs,Australia,DevMailAustralia1
haud0002,haud0002.tools4j.com,dev,dev-2,exchange,~/exchange,~/exchange/logs,Australia,DevExchangeAustralia
haud0002,haud0002.tools4j.com,dev,dev-2,mailserver,~/mailserver,~/mailserver/logs,Australia,DevMailAustralia2
haud0003,haud0003.tools4j.com,dev,dev-3,messaging,~/messaging,~/messaging/logs,Australia,DevMsgAustralia
hauu0001,hauu0001.tools4j.com,uat,uat-1,webserver,~/webserver,~/webserver/logs,Australia,UatWebAustralia
hauu0001,hauu0001.tools4j.com,uat,uat-1,mailserver,~/mailserver,~/mailserver/logs,Australia,UatMailAustralia1
hauu0002,hauu0002.tools4j.com,uat,uat-2,exchange,~/exchange,~/exchange/logs,Australia,UatExchangeAustralia
hauu0002,hauu0002.tools4j.com,uat,uat-2,mailserver,~/mailserver,~/mailserver/logs,Australia,UatMailAustralia2
hauu0003,hauu0003.tools4j.com,uat,uat-3,messaging,~/messaging,~/messaging/logs,Australia,UatMsgAustralia1
hauu0004,hauu0004.tools4j.com,uat,uat-4,messaging,~/messaging,~/messaging/logs,Australia,UatMsgAustralia2
hauu0005,hauu0005.tools4j.com,uat,uat-5,webserver,~/webserver,~/webserver/logs,London,UatWebLondon
hauu0005,hauu0005.tools4j.com,uat,uat-5,mailserver,~/mailserver,~/mailserver/logs,London,UatMailLondon1
hauu0006,hauu0006.tools4j.com,uat,uat-6,exchange,~/exchange,~/exchange/logs,London,UatExchangeLondon
hauu0006,hauu0006.tools4j.com,uat,uat-6,mailserver,~/mailserver,~/mailserver/logs,London,UatMailLondon2
hauu0007,hauu0007.tools4j.com,uat,uat-7,messaging,~/messaging,~/messaging/logs,London,UatMsgLondon1
hauu0008,hauu0008.tools4j.com,uat,uat-8,messaging,~/messaging,~/messaging/logs,London,UatMsgLondon2
hauu0009,hauu0009.tools4j.com,uat,uat-9,webserver,~/webserver,~/webserver/logs,Tokyo,UatWebTokyo
hauu0009,hauu0009.tools4j.com,uat,uat-9,mailserver,~/mailserver,~/mailserver/logs,Tokyo,UatMailTokyo1
hauu0010,hauu0010.tools4j.com,uat,uat-10,exchange,~/exchange,~/exchange/logs,Tokyo,UatExchangeTokyo
hauu0010,hauu0010.tools4j.com,uat,uat-10,mailserver,~/mailserver,~/mailserver/logs,Tokyo,UatMailTokyo2
hauu0011,hauu0011.tools4j.com,uat,uat-11,messaging,~/messaging,~/messaging/logs,Tokyo,UatMsgTokyo1
hauu0012,hauu0012.tools4j.com,uat,uat-12,messaging,~/messaging,~/messaging/logs,Tokyo,UatMsgTokyo2"""

    def "test 1"() {
        given:
        final DataSetFromCsvFile csvDataFile = new DataSetFromCsvFile(new CsvFile(new StringReader(csv)));

        when:
        final DataSet dataSet = csvDataFile.load();
        final PartIndex partIndex = new PartIndex(dataSet)
        final Results results = partIndex.search("dev").withAllWordsMatching()

        then:
        assert results.size() == 5
        assert results.getIndexes() == [0,1,2,3,4]
    }

    def "test 2"() {
        given:
        final DataSetFromCsvFile csvDataFile = new DataSetFromCsvFile(new CsvFile(new StringReader(csv)));

        when:
        final DataSet dataSet = csvDataFile.load();
        final PartIndex partIndex = new PartIndex(dataSet)
        final Results results = partIndex.search("UML").withAllWordsMatching()
        println results.toPrettyString()

        then:
        assert results.size() == 12
        assert results.getIndexes() == [12, 14, 15, 16, 6, 8, 9, 10, 18, 20, 21, 22]
    }

    def "test 3"() {
        given:
        final DataSetFromCsvFile csvDataFile = new DataSetFromCsvFile(new CsvFile(new StringReader(csv)));

        when:
        final DataSet dataSet = csvDataFile.load();
        final PartIndex partIndex = new PartIndex(dataSet)
        final Results results = partIndex.search("UatMsgLondon").withAllWordsMatching()
        println results.toPrettyString()

        then:
        assert results.size() == 2
        assert results.getIndexes() == [15, 16]
    }
}
