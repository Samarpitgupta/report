package open.report.report_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @RequestMapping("/getReport")
    public Object getReport() {
        return "Hello";
    }

}
