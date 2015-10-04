package business

import grails.transaction.Transactional
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord

@Transactional
class BusinessParserService {

    def parse(Reader input) {
        List<Business> list = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(input);
        for (CSVRecord record : records) {
            String name = record.get(0);
            String email = record.get(1);
            String adr = record.get(2);
            String index = record.get(3);
            if (name == 'name' && email == 'email') continue
            list.add(new Business(name: name, email: email, street: adr, zip: index))
        }
        return list
    }

    def save(list) {
        int added = 0
        int updated = 0
        for (Business biz : list) {
            Business saved = Business.findByEmail(biz.email)
            if (saved) {
                saved.name = biz.name
                saved.street = biz.street
                saved.zip = biz.zip
                saved.save()
                updated++
            } else {
                biz.save()
                added++
            }
        }
        return [added, updated]
    }

    def test() {
        Reader inp = new FileReader("business.csv");
        return save(parse(inp))
    }
}
