package com.cloudinary.provisioning;

import androidx.autofill.HintConstants;
import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Util;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class Account {
    public static final String ACCOUNTS = "accounts";
    private static final String CLOUDINARY_ACCOUNT_URL = "CLOUDINARY_ACCOUNT_URL";
    public static final String PROVISIONING = "provisioning";
    public static final String USERS = "users";
    public static final String USER_GROUPS = "user_groups";
    private final String accountId;
    private final Api api;
    private final AccountConfiguration configuration;
    private final String key;
    private final String secret;

    public Account(Cloudinary cloudinary) {
        String provisioningData = System.getProperty(CLOUDINARY_ACCOUNT_URL, System.getenv(CLOUDINARY_ACCOUNT_URL));
        if (provisioningData != null) {
            this.configuration = AccountConfiguration.from(provisioningData);
            this.accountId = this.configuration.accountId;
            this.key = this.configuration.provisioningApiKey;
            this.secret = this.configuration.provisioningApiSecret;
            this.api = cloudinary.api();
            return;
        }
        throw new IllegalArgumentException("Must provide configuration instance or set an ENV variable: CLOUDINARY_ACCOUNT_URL=account://provisioning_api_key:provisioning_api_secret@account_id");
    }

    public Account(AccountConfiguration accountConfiguration, Cloudinary cloudinary) {
        this.configuration = accountConfiguration;
        this.api = cloudinary.api();
        this.accountId = accountConfiguration.accountId;
        this.key = accountConfiguration.provisioningApiKey;
        this.secret = accountConfiguration.provisioningApiSecret;
    }

    private ApiResponse callAccountApi(Api.HttpMethod method, List<String> uri, Map<String, Object> params, Map<String, Object> options) throws Exception {
        Map<String, Object> options2 = verifyOptions(options);
        if (options2.containsKey("provisioning_api_key")) {
            if (!options2.containsKey("provisioning_api_secret")) {
                throw new IllegalArgumentException("When providing key or secret through options, both must be provided");
            }
        } else if (!options2.containsKey("provisioning_api_secret")) {
            options2.put("provisioning_api_key", this.key);
            options2.put("provisioning_api_secret", this.secret);
        } else {
            throw new IllegalArgumentException("When providing key or secret through options, both must be provided");
        }
        Util.clearEmpty(params);
        return this.api.getStrategy().callAccountApi(method, uri, params, options2);
    }

    public enum Role {
        MASTER_ADMIN("master_admin"),
        ADMIN("admin"),
        TECHNICAL_ADMIN("technical_admin"),
        BILLING("billing"),
        REPORTS("reports"),
        MEDIA_LIBRARY_ADMIN("media_library_admin"),
        MEDIA_LIBRARY_USER("media_library_user");

        private final String serializedValue;

        Role(String serializedValue) {
            this.serializedValue = serializedValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.serializedValue;
        }
    }

    public ApiResponse subAccount(String subAccountId) throws Exception {
        return subAccount(subAccountId, Collections.emptyMap());
    }

    public ApiResponse subAccount(String subAccountId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, "sub_accounts", subAccountId);
        return callAccountApi(Api.HttpMethod.GET, uri, Collections.emptyMap(), options);
    }

    public ApiResponse subAccounts(Boolean enabled, List<String> ids, String prefix) throws Exception {
        return subAccounts(enabled, ids, prefix, Collections.emptyMap());
    }

    public ApiResponse subAccounts(Boolean enabled, List<String> ids, String prefix, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, "sub_accounts");
        return callAccountApi(Api.HttpMethod.GET, uri, ObjectUtils.asMap("accountId", this.accountId, "enabled", enabled, "ids", ids, "prefix", prefix), options);
    }

    public ApiResponse createSubAccount(String name, String cloudName, Map customAttributes, boolean enabled, String baseAccount) throws Exception {
        return createSubAccount(name, cloudName, customAttributes, enabled, baseAccount, Collections.emptyMap());
    }

    public ApiResponse createSubAccount(String name, String cloudName, Map customAttributes, boolean enabled, String baseAccount, Map<String, Object> options) throws Exception {
        Map<String, Object> options2 = verifyOptions(options);
        options2.put("content_type", "json");
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, "sub_accounts");
        return callAccountApi(Api.HttpMethod.POST, uri, ObjectUtils.asMap("cloud_name", cloudName, HintConstants.AUTOFILL_HINT_NAME, name, "custom_attributes", customAttributes, "enabled", Boolean.valueOf(enabled), "base_sub_account_id", baseAccount), options2);
    }

    public ApiResponse updateSubAccount(String subAccountId, String name, String cloudName, Map<String, String> customAttributes, Boolean enabled) throws Exception {
        return updateSubAccount(subAccountId, name, cloudName, customAttributes, enabled, Collections.emptyMap());
    }

    public ApiResponse updateSubAccount(String subAccountId, String name, String cloudName, Map<String, String> customAttributes, Boolean enabled, Map<String, Object> options) throws Exception {
        Map<String, Object> options2 = verifyOptions(options);
        options2.put("content_type", "json");
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, "sub_accounts", subAccountId);
        return callAccountApi(Api.HttpMethod.PUT, uri, ObjectUtils.asMap("cloud_name", cloudName, HintConstants.AUTOFILL_HINT_NAME, name, "custom_attributes", customAttributes, "enabled", enabled), options2);
    }

    public ApiResponse deleteSubAccount(String subAccountId) throws Exception {
        return deleteSubAccount(subAccountId, Collections.emptyMap());
    }

    public ApiResponse deleteSubAccount(String subAccountId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, "sub_accounts", subAccountId);
        return callAccountApi(Api.HttpMethod.DELETE, uri, Collections.emptyMap(), options);
    }

    public ApiResponse user(String userId) throws Exception {
        return user(userId, null);
    }

    public ApiResponse user(String userId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USERS, userId);
        return callAccountApi(Api.HttpMethod.GET, uri, Collections.emptyMap(), options);
    }

    public ApiResponse users(Boolean pending, List<String> userIds, String prefix, String subAccountId) throws Exception {
        return users(pending, userIds, prefix, subAccountId, null);
    }

    public ApiResponse users(Boolean pending, List<String> userIds, String prefix, String subAccountId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USERS);
        return callAccountApi(Api.HttpMethod.GET, uri, ObjectUtils.asMap("accountId", this.accountId, "pending", pending, "ids", userIds, "prefix", prefix, "sub_account_id", subAccountId), options);
    }

    public ApiResponse createUser(String name, String email, Role role, List<String> subAccountsIds) throws Exception {
        return createUser(name, email, role, subAccountsIds, null);
    }

    public ApiResponse createUser(String name, String email, Role role, List<String> subAccountsIds, Map<String, Object> options) throws Exception {
        return createUser(name, email, role, null, subAccountsIds, options);
    }

    public ApiResponse createUser(String name, String email, Role role, Boolean enabled, List<String> subAccountsIds, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USERS);
        return performUserAction(Api.HttpMethod.POST, uri, email, name, role, enabled, subAccountsIds, options);
    }

    public ApiResponse updateUser(String userId, String name, String email, Role role, List<String> subAccountsIds) throws Exception {
        return updateUser(userId, name, email, role, subAccountsIds, null);
    }

    public ApiResponse updateUser(String userId, String name, String email, Role role, List<String> subAccountsIds, Map<String, Object> options) throws Exception {
        return updateUser(userId, name, email, role, null, subAccountsIds, options);
    }

    public ApiResponse updateUser(String userId, String name, String email, Role role, Boolean enabled, List<String> subAccountsIds, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USERS, userId);
        return performUserAction(Api.HttpMethod.PUT, uri, email, name, role, enabled, subAccountsIds, options);
    }

    public ApiResponse deleteUser(String userId) throws Exception {
        return deleteUser(userId, null);
    }

    public ApiResponse deleteUser(String userId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USERS, userId);
        return callAccountApi(Api.HttpMethod.DELETE, uri, Collections.emptyMap(), options);
    }

    public ApiResponse createUserGroup(String name) throws Exception {
        return createUserGroup(name, null);
    }

    public ApiResponse createUserGroup(String name, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS);
        return callAccountApi(Api.HttpMethod.POST, uri, ObjectUtils.asMap(HintConstants.AUTOFILL_HINT_NAME, name), options);
    }

    public ApiResponse updateUserGroup(String groupId, String name) throws Exception {
        return updateUserGroup(groupId, name, null);
    }

    public ApiResponse updateUserGroup(String groupId, String name, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS, groupId);
        return callAccountApi(Api.HttpMethod.PUT, uri, ObjectUtils.asMap(HintConstants.AUTOFILL_HINT_NAME, name), options);
    }

    public ApiResponse deleteUserGroup(String groupId) throws Exception {
        return deleteUserGroup(groupId, null);
    }

    public ApiResponse deleteUserGroup(String groupId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS, groupId);
        return callAccountApi(Api.HttpMethod.DELETE, uri, Collections.emptyMap(), options);
    }

    public ApiResponse addUserToGroup(String groupId, String userId) throws Exception {
        return addUserToGroup(groupId, userId, null);
    }

    public ApiResponse addUserToGroup(String groupId, String userId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS, groupId, USERS, userId);
        return callAccountApi(Api.HttpMethod.POST, uri, Collections.emptyMap(), options);
    }

    public ApiResponse removeUserFromGroup(String groupId, String userId) throws Exception {
        return removeUserFromGroup(groupId, userId, null);
    }

    public ApiResponse removeUserFromGroup(String groupId, String userId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS, groupId, USERS, userId);
        return callAccountApi(Api.HttpMethod.DELETE, uri, Collections.emptyMap(), options);
    }

    public ApiResponse userGroup(String groupId) throws Exception {
        return userGroup(groupId, null);
    }

    public ApiResponse userGroup(String groupId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS, groupId);
        return callAccountApi(Api.HttpMethod.GET, uri, Collections.emptyMap(), options);
    }

    public ApiResponse userGroups() throws Exception {
        return userGroups(Collections.emptyMap());
    }

    public ApiResponse userGroups(Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS);
        return callAccountApi(Api.HttpMethod.GET, uri, Collections.emptyMap(), options);
    }

    public ApiResponse userGroupUsers(String groupId) throws Exception {
        return userGroupUsers(groupId, null);
    }

    public ApiResponse userGroupUsers(String groupId, Map<String, Object> options) throws Exception {
        List<String> uri = Arrays.asList(PROVISIONING, ACCOUNTS, this.accountId, USER_GROUPS, groupId, USERS);
        return callAccountApi(Api.HttpMethod.GET, uri, Collections.emptyMap(), options);
    }

    private ApiResponse performUserAction(Api.HttpMethod method, List<String> uri, String email, String name, Role role, Boolean enabled, List<String> subAccountsIds, Map<String, Object> options) throws Exception {
        Map<String, Object> options2 = verifyOptions(options);
        options2.put("content_type", "json");
        return callAccountApi(method, uri, ObjectUtils.asMap("email", email, HintConstants.AUTOFILL_HINT_NAME, name, "role", role == null ? null : role.serializedValue, "enabled", enabled, "sub_account_ids", subAccountsIds), options2);
    }

    private Map<String, Object> verifyOptions(Map<String, Object> options) {
        if (options == null || options == Collections.EMPTY_MAP) {
            return new HashMap(2);
        }
        return options;
    }
}
