# Control display date format independent of browser settings.

# 📦 Deployment Guide

## 1. Deploy JAR

Copy files to:

```
C:\payara6\glassfish\domains\production\applications\Talon\WEB-INF\lib
```

Required:

* `datetime-uk-extension-v1.0.jar`
* `kotlin-stdlib-2.2.10.jar`

---

## 2. Configure TALON

### 2.1 `web.xml`

Path:

```
C:\payara6\glassfish\domains\production\applications\Talon\WEB-INF\web.xml
```

Add filter **before other filters**:

```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <display-name>Talon</display-name>
    <welcome-file-list>
        <welcome-file>LoginSuccess</welcome-file>
        <welcome-file>faces/MENU/MENU.xhtml</welcome-file>
    </welcome-file-list>

    <!-- /////////////// new module /////////////// -->
    <filter>
        <filter-name>LanguageFilter</filter-name>
        <filter-class>com.brightbetter.talon.datetime.LanguageFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>LanguageFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!-- //////////////////////////////////////// -->

    ....

</web-app>
```

---

### 2.2 `faces-config.xml`

Path:

```
C:\payara6\glassfish\domains\production\applications\Talon\WEB-INF\faces-config.xml
```

Add inside `<application>`:

```xml
<faces-config>

    ....

    <application>

        ....

        <locale-config>
            <default-locale>ja_JP</default-locale>
            <supported-locale>ja</supported-locale>
            <supported-locale>ja_JP</supported-locale>
            <supported-locale>en</supported-locale>
            <supported-locale>en_US</supported-locale>
            <supported-locale>zh</supported-locale>
            <supported-locale>zh_TW</supported-locale>
            <supported-locale>zh</supported-locale>
            <supported-locale>zh_CN</supported-locale>
        </locale-config>
        <el-resolver
        >org.primefaces.application.exceptionhandler.PrimeExceptionHandlerELResolver</el-resolver>

        <!-- /////////////// new module /////////////// -->
        <view-handler>com.brightbetter.talon.datetime.LanguageViewHandler</view-handler>
        <!-- //////////////////////////////////////// -->

    </application>
</faces-config>
```

---

## 3. UI Date Format

### File:

```
C:\payara6\glassfish\domains\production\applications\Talon\TALON\APPLICATION\GENERALFREE\GENERALFREE.xhtml
```

### Add inside `<script>` (around line 59):

```javascript
flatpickrOption_DATE.dateFormat = "d/m/Y";
flatpickrOption_DATE_NO_SEP.dateFormat = "dmY";
flatpickrOption_DATE_TIME.dateFormat = "d/m/Y H:i:S";
flatpickrOption_DATE_HM.dateFormat = "d/m/Y H:i";
```

### Example:

```xhtml
<tln:JsonData id="TLN_JSON_DATA"
              freeController="#{tLN_GENERALFREEController}"
              selectItemList="#{selectItemList}"
              commonBean="#{tLN_GENERAL_COMMON_Bean}"/>

<script type="text/javascript">

<tln:JsonData id="TLN_JSON_DATA" freeController="#{tLN_GENERALFREEController}" 
              selectItemList="#{selectItemList}" commonBean="#{tLN_GENERAL_COMMON_Bean}"/>
    <script type="text/javascript">
    //<![CDATA[
    
    flatpickrOption_DATE.dateFormat = "d/m/Y";
    flatpickrOption_DATE_NO_SEP.dateFormat = "dmY";
    flatpickrOption_DATE_TIME.dateFormat = "d/m/Y H:i:S";
    flatpickrOption_DATE_HM.dateFormat = "d/m/Y H:i";
    
    let flatpickrMap = { 'DATE' : flatpickrOption_DATE, 'DATE_NO_SEP' : flatpickrOption_DATE_NO_SEP, 'DATE_TIME' : flatpickrOption_DATE_TIME };
    const isPositionDataEmpty = #{empty( tLN_GENERALFREEController.parentForm.parentPosition )?true:tLN_GENERALFREEController.parentForm.parentPosition.isPosDataEmpty};
    setIsUseTabIndex( #{tLN_GENERALFREEController.parentForm.isUseTabIndex} );
    
    ....
    
    //]]>
</script>
```

---

## 4. Restart

Restart Payara after deployment.
