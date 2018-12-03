<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" src="${ctx }/web/plupload-2.3.1/plupload.full.min.js"></script>
<script>

var resourceids=[];
function pladdFileId(fileId,id){
	var row = document.getElementById("attachement" + fileId);
	var resourceId = id.split(',')[2];
	row.cells[4].innerHTML = resourceId;
	$("#dl_"+ fileId).click(function(){
		toDownloadPage(resourceId);
	});
    resourceids.push(resourceId);
    $("#fileIds").val(resourceids.join(","));
}

function pladdFileInfo(fileId,message){
	var row = document.getElementById(fileId);
	row.cells[2].innerHTML = "<font color='green'>"+message+"</font>";
}

function pldeleteFile(fileId){
	$.messager.confirm('确认', '是否确认删除此文件?', function(r){
		if(r){
			//用表格显示
			var infoTable = document.getElementById("filelist");
			var row = document.getElementById(fileId);
			var filePath = "";
			var resourceid = row.cells[4].innerHTML.split(",")[0];
			//删除上传成功的文件
			$.ajax({
				type : 'post',
				url : ctx+"/newsUpload/deleteFile",
				data : 'id='+resourceid,
				dataType: "json",
				success : function(data) { // 判断是否成功
					if(data.status=='success'){
						//处理被删除的节点
						infoTable.deleteRow(row.rowIndex);
						$("#filelist tr").each(
							function(){
							    var temp=$("td:eq(4)",this).text().split(",");
							    resourceids.push(temp[2]);
							}
						);
						$("#fileIds").val(resourceids.join(","));
	                }else{
	                	$.messager.alert('提示',data.msg,'error');
	                }
				},
				error:function(data){
					pladdFileInfo(fileId,"<font color='red'>删除文件出错</a>");
				}
			});
		}
	})
	
	
	
}

Date.prototype.format = function(format){
	 var o = {
		 "M+" : this.getMonth()+1, //month
		 "d+" : this.getDate(),    //day
		 "h+" : this.getHours(),   //hour
		 "m+" : this.getMinutes(), //minute
		 "s+" : this.getSeconds(), //second
		 "q+" : Math.floor((this.getMonth()+3)/3), 
		 "S" : this.getMilliseconds() //millisecond
	 }
	 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	 for(var k in o){
		 if(new RegExp("("+ k +")").test(format)){
			 format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		 }
	 } 
	 return format;
}
$(function(){
	var uploader = new plupload.Uploader({
		flash_swf_url : ctx + '/web/plupload-2.3.1/Moxie.swf',
        url : ctx+"/newsUpload/fileUpload?userId=${userBean.id}",
        browse_button : 'uploader', 
        filters: {
        	  max_file_size : '100mb',//100b, 10kb, 10mb, 1gb
        	  prevent_duplicates : true //不允许选取重复文件
        },
        init : {
			FilesAdded: function(up, files) {
				var fileTrs = "";
				for(var i = 0; i < files.length; i++){
					var date = new Date();
					var dateStr = date.format("yyyy-MM-dd hh:mm:ss");
					fileTrs += "<tr id='attachement" + files[i].id+ "'>"
						+"<td width=\"27px\" align=\"center\"></td>"
						+"<td width=\"350px\" nowrap=\"nowrap\">"
						+"<span style=\"cursor:pointer\"  id=\"dl_"+ files[i].id +"\" >"+files[i].name+"</span></td>"
						+"<td width=\"70px\" align=\"center\"><span class=\"green\" id=\""+files[i].id+"\">开始上传</span></td>"
						+"<td width=\"50px\" align=\"center\"><div style=\"cursor:pointer;\" onclick=\"pldeleteFile('attachement" + files[i].id+ "')\">删除</div></td>"
						+"<td style=\"display:none\"></td>"
						+"<td>"+dateStr+"</td>"
						+"<td style=\"display:none\"></td>"
						+"</tr>";
				}
				$("#filelist").append(fileTrs);
				uploader.start();
				return false;
			},
            FileUploaded : function(up, file, info) {//文件上传完毕触发
                $("#"+file.id).html("上传完成");
                var resp = info.response;
            	pladdFileId(file.id ,resp);
            },
            UploadComplete : function( uploader,files ) {
            	console.log("所有文件上传完毕");
            },
            UploadProgress : function( uploader,file ) {
            	$("#"+file.id).html("上传进度为：" + file.percent + "%");
            },
            Error:function( uploader,errObject){
            	var errorCode = errObject.code;
            	var errorMsg = errObject.message;
            	if(errorCode == -600){
            		$.messager.alert('提示',"允许上传的单个文件最大为100 MB！",'info');
            	}else{
            		alert(errorMsg);
            	}
            }
        }
    });
	uploader.init();
});


function toDownloadPage(fileId){
	window.open('${ctx }/newsUpload/fileDownload?fileId='+fileId);
}

function toDownloads(){
	var boo = false;
	var id = "";
	$('#filelist tr').each(function(i){                   // 遍历 tr
        $(this).children('td').each(function(j){  // 遍历 tr 的各个 td
			if(j == 0) {
				$(this).children('input').each(function(h){
						if(h == 0) {
							if($(this).is(':checked')){
								boo = true;
							}else {
								boo =  false;
							}
						}
						if(h == 1) {
							id = $(this).val();
							
						}
					}
				)
				if(boo) {
					window.open('${ctx }/newsUpload/fileDownload?fileId='+id);
					boo = false; 
				}
			}
       });
    })
}
function check(){
	if($("#checkbox").is(':checked')) {
		$('#filelist tr').each(function(i){                   // 遍历 tr
	        $(this).children('td').each(function(j){  // 遍历 tr 的各个 td
				if(j == 0) {
					$(this).children('input').each(function(h){
							if(h == 0) {
								$(this).attr("checked",true);
							}
						}
					)
				}
	       });
	    })
	}else{
		$('#filelist tr').each(function(i){                   // 遍历 tr
	        $(this).children('td').each(function(j){  // 遍历 tr 的各个 td
				if(j == 0) {
					$(this).children('input').each(function(h){
							if(h == 0) {
								$(this).attr("checked",false);
							}
						}
					)
				}
	       });
	    })
	}
}

</script>
<div id="uploaderContorl">
&nbsp;&nbsp;<span id="uploader" style="cursor:pointer;" class="uploader-btn">选择上传文档</span>
</div>
	<input type="hidden" name="fileIds" id="fileIds" value="${bean.fileIds}"/>
	<table id="filelist" style="width:100%;" class="formtable">	
		<c:forEach items="${attachmentList }" var="attachment" varStatus="i">
			<tr id="attachement${i.index }">
				<%-- <td width="27px" align="center"><input type='checkbox'><input type="hidden" value='${attachment.id}'/></td> --%>
				<td width="27px" align="center"><input type='checkbox'><input type="hidden" value='${attachment.resourceid}'/></td>
				<td width="350px" nowrap="nowrap">
					<%-- <span style="cursor:pointer" id='onclickOfAttachement' onclick="toDownloadPage('${attachment.id }')" target="_blank">${attachment.fileName }</span> --%>
					<span style="cursor:pointer" id='onclickOfAttachement' onclick="toDownloadPage('${attachment.resourceid }')" target="_blank">${attachment.fileName }</span>
				</td>
				<td width="120px"  align="center">已上传</td>
				<!-- 当登录人员与附件上传人员一致，并且流程状态为活动代办时可以删除该人员已上传附件 -->
				<c:choose>
					<c:when test="${userBean.id==attachment.creatorId}">
						<td width="100px" align="center">
						<div style="cursor:pointer;" onclick="pldeleteFile('attachement${i.index }')">
						删除
						</div>
						</td>
					</c:when>
					<c:otherwise>
						<td width="100px" align="center">&nbsp;</td>
					</c:otherwise>
				</c:choose>
				<%-- <td style="display:none">${attachment.resourceid },${attachment.storagePath },${attachment.fileSize },${attachment.fileName }</td>
				<td width="250px"  align="left">【${attachment.creatorName}】于&nbsp;<fmt:formatDate value="${attachment.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;时上传</td>
				<td style="display:none">${attachment.fileUrl},${attachment.creatorName},<fmt:formatDate value="${attachment.creatorName}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
				<td style="display:none">${attachment.resourceid },${attachment.storagePath },${attachment.fileSize },${attachment.fileName }</td>
				<td width="250px"  align="left">【${attachment.creatorName}】于&nbsp;<fmt:formatDate value="${attachment.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;时上传</td>
				<%-- <td style="display:none">${attachment.fileUrl},${attachment.creatorName},<fmt:formatDate value="${attachment.creatorName}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
			</tr>
		</c:forEach>
	</table>
