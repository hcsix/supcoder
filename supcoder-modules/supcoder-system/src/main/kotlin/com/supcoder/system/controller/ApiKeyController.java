package com.supcoder.system.controller;


/**
 * ApiKeyController
 *
 * @author lee
 * @date 2024/12/12
 */

import com.supcoder.hub.core.apikey.ApiKeyGenerator;
import com.supcoder.hub.core.checker.ServiceChecker;
import com.supcoder.hub.core.checker.PermissionChecker;
import com.supcoder.hub.core.model.ApiKeyModel;
import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import com.supcoder.system.dashboard.auth.JwtUtil;
import com.supcoder.hub.db.domain.ApiCallLogs;
import com.supcoder.hub.db.domain.ApiKeys;
import com.supcoder.hub.db.model.ApiKeyRequest;
import com.supcoder.hub.db.model.ApiCallLog;
import com.supcoder.hub.db.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/apikey")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;


    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/create")
    public ResponseEntity<JsonResult<ApiKeyModel>> createApiKey(@RequestBody ApiKeyRequest request,
                                                                @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            return ResponseEntity.ok(ResultUtil.unAuthorized("Authorization header is missing"));
        }
        String username = jwtUtil.extractUsername(authorization);
        if (username == null) {
            return ResponseEntity.ok(ResultUtil.unAuthorized("Invalid authorization token"));
        }
        if (!ServiceChecker.isSupportedService(request.getService())) {
            return ResponseEntity.ok(ResultUtil.badArgumentValue());
        }
        if (!PermissionChecker.isFormatValid(request.getPermissions())) {
            return ResponseEntity.ok(ResultUtil.badArgumentValue());
        }
        ApiKeyModel key = ApiKeyGenerator.generateApiKey();
        apiKeyService.createApiKey(key.getApiKey(), key.getSecretKey(), username, request.getPermissions(), request.getService());
        return ResponseEntity.ok(ResultUtil.success(key));
    }

    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<ApiKeys>>> listApiKeys(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            return ResponseEntity.ok(ResultUtil.unAuthorized("Authorization header is missing"));
        }
        String username = jwtUtil.extractUsername(authorization);
        if (username == null) {
            return ResponseEntity.ok(ResultUtil.unAuthorized("Invalid authorization token"));
        }
        List<ApiKeys> apiKeys = apiKeyService.getApiKeyList(username);
        return ResponseEntity.ok(ResultUtil.success(apiKeys));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult<Void>> deleteApiKey(@PathVariable String id) {

        boolean isDeleted = apiKeyService.deleteApiKey(Integer.valueOf(id));
        if (!isDeleted) {
            return ResponseEntity.ok(ResultUtil.notFound("API Key not found"));
        }
        return ResponseEntity.ok(ResultUtil.success(null));
    }


    @GetMapping("/logs")
    public ResponseEntity<JsonResult<List<ApiCallLog>>> getApiCallLogs(@RequestParam String apiKeyId,
                                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
                                                                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate
    ) {
        List<ApiCallLogs> logs = apiKeyService.getApiCallLogs(Integer.valueOf(apiKeyId), startDate, endDate);
        return ResponseEntity.ok(ResultUtil.success(logs));
    }
}
