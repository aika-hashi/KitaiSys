'use strict'

/** HTTPレスポンスヘッダから現在時刻取得 */
function twoDigit(num) {
      let ret;
      if( num < 10 ) 
        ret = "0" + num; 
      else 
        ret = num; 
      return ret;
    }

function showClock() {		
			let nowTime = new Date();
			let nowHour = twoDigit( nowTime.getHours());
			let nowMin = twoDigit(nowTime.getMinutes());
			let nowSec = twoDigit(nowTime.getSeconds());
			let msg = nowHour + ":" + nowMin + ":" + nowSec;
			document.getElementById("nowTime").innerHTML = msg;	
}
setInterval('showClock()', 1000);




/** 画面ロード時の処理. */
jQuery(function($) {

	/** トップ画面に遷移した時の現在時刻. */
	$('#nowTime').ready(function(event) {
		//出勤ボタン更新
		showClock();
	});

	/** 出勤ボタンを押したときの処理. */
	$('#btn-beginTime').click(function(event) {
		//出勤ボタン更新
		updateTime();
	});

	/** 退勤ボタンを押したときの処理. */
	$('#btn-endTime').click(function(event) {
		//退勤ボタン更新
		updateTime();
	});

	/** 外出ボタンを押したときの処理. */
	$('#btn-outingTime').click(function(event) {
		//外出ボタン更新
		updateTime();
	});

	/** 帰社ボタンを押したときの処理. */
	$('#btn-returnTime').click(function(event) {
		//帰社ボタン更新
		updateTime();
	});

　

});



/** 時刻新規登録処理 一応*/
function signupUser() {

	// バリデーション結果をクリア
	removeValidResult();

	// フォームの値を取得
	var formData = $('#taskTop-form').serializeArray();

	// ajax通信
	$.ajax({
		type: "POST",
		cache: false,
		url: '/task/top/update',
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
		url: '/task/top/update',
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
	
}

