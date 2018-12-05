package ws.cpcs.testapp.api.endpoints;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/version")
@Api(tags = "Version")
public class Version {

    @Value("${app.version}")
    private String version;

    @Value("${app.build}")
    private String build;

    @GetMapping
    @ApiOperation("Version information")
    VersionRes getVersion() {
        return new VersionRes();
    }

    class VersionRes {

        public String getVersion() {
            return version;
        }

        public String getBuild() {
            return build;
        }
    }
}
