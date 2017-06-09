var SERVERURL;
var GROUP;
var DOCURL;
var ONE;

$(function () {
    var path = window.location.href;
    var base = path.split("/apidoc")[0];
    SERVERURL = base;
    DOCURL = SERVERURL + "/api-docs";
    versionGenerator();
});

function versionGenerator() {
    $.get(SERVERURL + "/api/version", function (versions) {
        var version = "<select onchange='chageApi(this.options[this.selectedIndex].value);' style='font-size:20px;color:blue'>\n";
        for (i = 0; i < versions.length; i++) {
            if (i === 0) {
                ONE = versions[i].version;
            }
            version += "<option  value=" + versions[i].version + ">" + versions[i].url + "</option>\n";
        }
        version += "</select>";
        $("#doc").html(version);
        chageApi(ONE);
    });
}

function chageApi(value) {
    GROUP = "?group=" + value;
    DOCURL = SERVERURL + "/api-docs" + GROUP;
    loadApiDoc();
}

function loadApiDoc() {
    window.swaggerUi = new SwaggerUi({
        url: DOCURL,
        dom_id: "swagger-ui-container",
        supportedSubmitMethods: ['get', 'post', 'put', 'delete', 'patch'],
        onComplete: function (swaggerApi, swaggerUi) {
            $('pre code').each(function (i, e) {
                hljs.highlightBlock(e);
            });
        },
        onFailure: function (data) {
            alert("无法加载接口，请联系相关开发人员");
        },
        docExpansion: "none",
        sorter: "alpha"
    });
    window.swaggerUi.load();

}

function copyApiUrl(data) {
    $('#currentApiUrl').html("当前api：" + data + " 【注意版本号】");
    window.console.log(data);
}