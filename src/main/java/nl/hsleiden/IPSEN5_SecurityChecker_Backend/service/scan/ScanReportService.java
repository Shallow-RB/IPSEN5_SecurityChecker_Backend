package nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.dao.ScanReportDao;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScanReportService {

    @Autowired
    private ScanReportDao scanReportDao;

    public List<ScanReport> getAll() {
        return this.scanReportDao.getAll();
    }

    public ScanReport get(Long id) {
        return this.scanReportDao.get(id);
    }

    public ScanReport create(ScanReport scanReport) {
        return this.scanReportDao.create(scanReport);
    }

    public void update(ScanReport scanReport) {
        this.scanReportDao.update(scanReport);
    }

    public void delete(ScanReport scanReport) {
        this.scanReportDao.delete(scanReport);
    }


    public ScanReport giveGradingToScanReport(ScanReport scanReport) {
        ArrayList<String> optionsForResult = new ArrayList<>();
        optionsForResult.add("grade");
        optionsForResult.add("result");
        optionsForResult.add("score");

        Map<String, Object> result = scanReport.getResult();
        for (String gradeOption : optionsForResult) {
            String grade = result.get(gradeOption).toString();
            if (grade != null) {
                scanReport.setGrade(Integer.parseInt(grade));
                break;
            } else {
                scanReport.setGrade(-1);
            }
        }
        return scanReport;
    }
}


