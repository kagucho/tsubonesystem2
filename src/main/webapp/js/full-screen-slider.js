/*
 * Copyright 2012 soundarapandian
 * Licensed under the Apache License, Version 2.0
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
$(document).ready(function() {
  /*********** Slider starts here *********/

  /*Duration of fade effect in milliseconds.
   * Change the duration as you like */
  var animationDuration = 10000;
  /*Time interval to change the slides in milliseconds.
   * Change the interval as you like */
  var animationInterval = 500;
  
  //Changes slide in the given time interval.
  function changeSlide() {
    var active = $(".full-screen-slider img.active");
    var next = active.next();
    if(next.length == 0) {
      next = $(".full-screen-slider img:first");
    }
    setTimeout(function() {
      fadeInSlide(next);
    }, 1);
    setTimeout(function() {
      fadeOutSlide(active, next);
    }, 1);
  }

  //Fadeout slide in the given time duration
  function fadeOutSlide(active, next) {
    active.fadeOut(animationDuration, function() {
      active.removeClass("active");
      next.addClass("active");
    });
  }

  //Fadein slide in the given time duration
  function fadeInSlide(next) {
    next.fadeIn(animationDuration);
  }

  //Initialize the slider control
  setInterval(changeSlide, animationInterval);
  
  /************* Slider ended **********************/
});
