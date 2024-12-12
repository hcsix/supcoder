package com.supcoder.hub.dashboard.controller;


/**
 * ApiKeyController
 *
 * @author lee
 * @date 2024/12/12
 */

import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import com.supcoder.hub.dashboard.auth.JwtUtil;
import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.model.ApiKey;
import com.supcoder.hub.db.model.ApiKeyRequest;
import com.supcoder.hub.db.model.ApiKeyUpdateRequest;
import com.supcoder.hub.db.model.RotatedApiKey;
import com.supcoder.hub.db.model.ApiKeyStatusRequest;
import com.supcoder.hub.db.model.ApiCallStatistics;
import com.supcoder.hub.db.model.ApiCallLog;
import com.supcoder.hub.db.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/apikey")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/create")
    public ResponseEntity<JsonResult<ApiKey>> createApiKey(@RequestBody ApiKeyRequest request,
                                                           @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            return ResponseEntity.badRequest().body("Authorization header is missing");
        }
        String username = jwtUtil.extractUsername(authorization);
        if (username == null) {
            return ResponseEntity.badRequest().body("Invalid authorization token");
        }
        User user = userService.queryByUsername(username);

        ApiKey apiKey = apiKeyService.createApiKey(request.getUserInfo(), request.getScope(), request.getService());
        return ResponseEntity.ok(ResultUtil.success(apiKey));
    }

    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<ApiKey>>> listApiKeys(@RequestParam(required = false) String userId,
                                                                @RequestParam String app_id,
                                                                @RequestParam String app_secret) {

        List<ApiKey> apiKeys = apiKeyService.listApiKeys(userId);
        return ResponseEntity.ok(ResultUtil.success(apiKeys));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult<ApiKey>> getApiKeyDetails(@PathVariable String id,
                                                               @RequestParam String app_id,
                                                               @RequestParam String app_secret) {
        ApiKey apiKey = apiKeyService.getApiKeyById(id);
        if (apiKey == null) {
            return ResponseEntity.ok(ResultUtil.error(404, "API Key not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(apiKey));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonResult<ApiKey>> updateApiKey(@PathVariable String id,
                                                           @RequestBody ApiKeyUpdateRequest request,
                                                           @RequestParam String app_id,
                                                           @RequestParam String app_secret) {

        ApiKey updatedApiKey = apiKeyService.updateApiKey(id, request.getScope(), request.getStatus());
        if (updatedApiKey == null) {
            return ResponseEntity.ok(ResultUtil.error(404, "API Key not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(updatedApiKey));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult<Void>> deleteApiKey(@PathVariable String id,
                                                         @RequestParam String app_id,
                                                         @RequestParam String app_secret) {

        boolean isDeleted = apiKeyService.deleteApiKey(id);
        if (!isDeleted) {
            return ResponseEntity.ok(ResultUtil.error(404, "API Key not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(null));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<JsonResult<ApiKey>> changeApiKeyStatus(@PathVariable String id,
                                                                 @RequestBody ApiKeyStatusRequest request,
                                                                 @RequestParam String app_id,
                                                                 @RequestParam String app_secret) {

        ApiKey apiKey = apiKeyService.changeApiKeyStatus(id, request.getStatus());
        if (apiKey == null) {
            return ResponseEntity.ok(ResultUtil.error(404, "API Key not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(apiKey));
    }

    @PatchMapping("/{id}/rotate")
    public ResponseEntity<JsonResult<RotatedApiKey>> rotateApiKey(@PathVariable String id,
                                                                  @RequestParam String app_id,
                                                                  @RequestParam String app_secret) {

        RotatedApiKey rotatedApiKey = apiKeyService.rotateApiKey(id);
        if (rotatedApiKey == null) {
            return ResponseEntity.ok(ResultUtil.error(404, "API Key not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(rotatedApiKey));
    }

    @GetMapping("/stats")
    public ResponseEntity<JsonResult<ApiCallStatistics>> getApiCallStatistics(@RequestParam(required = false) String userId,
                                                                              @RequestParam(required = false) String service,
                                                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                              @RequestParam String app_id,
                                                                              @RequestParam String app_secret) {

        ApiCallStatistics stats = apiKeyService.getApiCallStatistics(userId, service, startDate, endDate);
        return ResponseEntity.ok(ResultUtil.success(stats));
    }

    @GetMapping("/logs")
    public ResponseEntity<JsonResult<List<ApiCallLog>>> getApiCallLogs(@RequestParam String apiKeyId,
                                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                       @RequestParam String app_id,
                                                                       @RequestParam String app_secret) {

        List<ApiCallLog> logs = apiKeyService.getApiCallLogs(apiKeyId, startDate, endDate);
        return ResponseEntity.ok(ResultUtil.success(logs));
    }
}
