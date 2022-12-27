'use strict'

var monthData = null;
var table = null;



/** 画面ロード時の処理. */
jQuery(function($) {
	 // DataTablesの初期化
 // createDataTables();
	
	/** ＜押下時. */
　　　　$('#btn-lastMonth').click(function(event) {
		//帰社ボタン更新
		lastMonth();
	});

/** ＞押下時. */

　　$('#btn-nextMonth').click(function(event) {
		//帰社ボタン更新
		nextMonth();
	});




});

/** 前月検索処理 */
	function lastMonth(){ {

  // formの値を取得
  var formData = $('#month-list-table').serialize();

  // ajax通信
  $.ajax({
    type : "GET",
    url : '/task/list/get/list',
    data: formData,
    dataType : 'json',
    contentType: 'application/json; charset=UTF-8',
    cache : false,
    timeout : 5000,
  }).done(function(data) {
    // ajax成功時の処理
    console.log(data);
    // JSONを変数に入れる
    monthData = data;
    // DataTables作成
    
//createDataTables();
    
    var calender = document.getElementById('calenderList');
    console.log('calender:'+calender);
    var days = document.getElementById('day');
     console.log('days:'+days);
    var nowMonth = calender.value;
    //console.log('nowMonth:'+nowMonth);
     nowMonth= monthData;
     console.log('nowMonth:'+nowMonth);
     days =nowMonth;
     console.log('days:'+days);
	
    alert('検索処理に成功しました');
    
    // ユーザー一覧画面にリダイレクト
    window.location.href = '/task/list';

    
    

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax失敗時の処理
    alert('検索処理に失敗しました');

  }).always(function() {
    // 常に実行する処理(特になし)
  });
}

/** DataTables作成. */
 /** function createDataTables() {

  //既にDataTablesが作成されている場合
  if(table !== null) {
    // DataTables破棄
    table.destroy();
  }

  // DataTables作成
  table = $('#month-list-table').DataTable({
    // 日本語化
    language: {
      url: '/webjars/datatables-plugins/i18n/Japanese.json'
    },
    //表示データ
    data: monthData,
    
  });
}*/

	
		
		
		
	}
	
	/** 翌月更新処理 */
	function nextMonth(){
		//現在日時取得
		//var dt2 = new Date();
		//dt2.setMonth(dt2.getMonth() + 1);
		//console.log(dt2);
		
		
	}






/** 月プルダウンselected設定 */

 
    var select = document.getElementById('month');
    //console.log('select:'+select);
    var date = new Date();
    //console.log('date:'+date);
    var nowMonth = select.value;
    //console.log('nowMonth:'+nowMonth);
     nowMonth= Number(date.getMonth());
     //console.log('nowMonth:'+nowMonth);
     //console.log('option:'+select.options[nowMonth]);
	select.options[nowMonth].selected = true;
	//console.log(document.getElementById('month').selectedIndex);

      
	

    
        
    




//以下コピペそのままもあります

/** 時刻新規登録処理 一応*/
function signupUser() {

	// バリデーション結果をクリア
	removeValidResult();

	// フォームの値を取得
	var formData = $('#task-form').serializeArray();

	// ajax通信
	$.ajax({
		type: "POST",
		cache: false,
		url: '/task/update',
		data: formData,
		dataType: 'json',
	}).done(function(data) {
		// ajax成功時の処理
		console.log(data);

		if (data.result === 90) {
			// validationエラー時の処理
			$.each(data.errors, function(key, value) {
				reflectValidResult(key, value)
			});

		} else if (data.result === 0) {
			alert('時刻を登録しました');
			// ログイン画面にリダイレクト
			window.location.href = '/task/top';
		}

	}).fail(function(jqXHR, textStatus, errorThrown) {
		// ajax失敗時の処理
		alert('登録に失敗しました');

	}).always(function() {
		// 常に実行する処理
	});
}

/** バリデーション結果をクリア */
function removeValidResult() {
	$('.is-invalid').removeClass('is-invalid');
	$('.invalid-feedback').remove();
	$('.text-danger').remove();
}

/** バリデーション結果の反映 */
function reflectValidResult(key, value) {

	// エラーメッセージ追加

	// CSS適用
	$('input[id=' + key + ']').addClass('is-invalid');
	// エラーメッセージ追加
	$('input[id=' + key + ']')
		.after('<div class="invalid-feedback">' + value + '</div>');
}



/** 時刻更新処理 */
function updateTime() {
	//フォームの値を取得
	var formData = $('#task-form').serializeArray();
	console.log(formData);

	//ajax通信
	$.ajax({
		type: "PUT",
		cache: false,
		url: '/task/update',
		data: formData,
		dataType: 'json',
	}).done(function(data) {
		console.log(data);
		//ajax成功時の処理	

		//現在日時取得
		var date = new Date();

		if (document.getElementById('btn-beginTime')) {
			data[3] = date;
			console.log(data[3]);
		} else if (document.getElementById('btn-endTime')) {
			formData[4] = date;
			console.log(data);
		} else if (document.getElementById('btn-outingTime')) {
			formData[5] = date;
			console.log(data);
		} else if (document.getElementById('btn-returnTime')) {
			formData[6] = date;
			console.log(data);

		}

		alert(data[3]);
		alert('ユーザを更新しました');
		//トップ画面みリダイレクト
		window.location.href = '/task/top';

	}).fail(function(jqXHR, textStatus, errorThrown) {
		//ajax失敗時の処理

		alert('ユーザ更新に失敗しました');

	}).always(function() {
		//常に実行処理する


	});
	
	/** 前月更新処理 */
	function lastMonth(){
		//現在日時取得
		var dt1 = new Date();
		dt1.setMonth(dt1.getMonth() - 1);
		console.log(dt1);
		
		
		
	}
	
	/** 翌月更新処理 */
	function nextMonth(){
		//現在日時取得
		var dt2 = new Date();
		dt2.setMonth(dt2.getMonth() + 1);
		console.log(dt2);
		
		
	}


}

